<script lang="ts">
  import { page } from '$app/stores';
  import { onMount } from 'svelte';

  let title = '';
  let item_id: number | null = null;
  let artist = '';
  let imageUrl = '';
  let auctionStart = '';
  let auctionEnd = '';
  let startingPrice = 0;
  let currentBid = 0;
  let category = '';
  let ownerId: string | null = null;

  let highestBidderId: string | null = null;
  let bidAmount = '';
  let memberId = '';
  let message = '';
  let auctionClosed = false;

  $: title = decodeURIComponent($page.params.title);

  async function fetchItem() {
    const res = await fetch(`http://localhost:8080/api/items?itemName=${encodeURIComponent(title)}`);
    const data = await res.json();
    const item = Array.isArray(data) ? data.find(i => i.itemName === title) : data;
    if (!item) {
      message = '❌ 해당 작품이 존재하지 않습니다.';
      return;
    }

    item_id = item.itemId;
    artist = item.artist;
    imageUrl = item.imageUrl;
    auctionStart = item.auctionStart;
    auctionEnd = item.auctionEnd;
    startingPrice = Number(item.startingPrice);
    currentBid = item.currentPrice ? Number(item.currentPrice) : 0;
    category = item.category ?? '';
    ownerId = item.ownerId ?? null;

    auctionClosed = new Date(auctionEnd) < new Date();
  }

  async function fetchHighestBidder() {
    if (!item_id) return;

    const res = await fetch(`http://localhost:8080/api/activities/item/${item_id}`);
    const activities = await res.json();

    if (Array.isArray(activities) && activities.length > 0) {
      activities.sort((a, b) => Number(b.price) - Number(a.price));

      currentBid = Number(activities[0].price);
      highestBidderId = activities[0].memberId ?? null;
    } else {
      currentBid = 0;
      highestBidderId = null;
    }
  }

  onMount(async () => {
    await fetchItem();
    if (item_id !== null) await fetchHighestBidder();
  });

  async function submitBid() {
    message = '';
    const bid = parseInt(bidAmount);
    const member = memberId.trim();

    if (!bidAmount || isNaN(bid) || bid <= 0) {
      message = '⚠ 유효한 입찰 금액을 입력하세요.';
      return;
    }

    if (!member) {
      message = '⚠ 유효한 회원 닉네임을 입력하세요.';
      return;
    }

    if (member === ownerId) {
      message = '⚠ 판매자는 자신의 물품에 입찰할 수 없습니다.';
      return;
    }

    if (member === highestBidderId) {
      message = '⚠ 현재 최고 입찰자는 연속으로 입찰할 수 없습니다.';
      return;
    }

    if (bid <= currentBid) {
      message = `⚠ 현재 입찰가 ₩${currentBid.toLocaleString()}보다 높은 금액을 입력하세요.`;
      return;
    }

    if (!item_id) {
      message = '⚠ 유효한 작품 ID가 없습니다.';
      return;
    }

    try {
      const activityRes = await fetch(`http://localhost:8080/api/activities`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
          memberId: member,
          itemId: item_id,
          price: bid
        })
      });

      if (!activityRes.ok) {
        const errText = await activityRes.text();
        message = `❌ 입찰 내역 저장 실패: ${errText}`;
        return;
      }

      const newActivity = await activityRes.json();

      highestBidderId = newActivity.memberId;
      currentBid = Number(newActivity.price);

      const updateBody = {
        artist,
        itemName: title,
        startingPrice,
        current_price: currentBid,
        auctionStart,
        auctionEnd,
        category,
        ownerId,
        imageUrl
      };

      const res = await fetch(`http://localhost:8080/api/items/${item_id}`, {
        method: 'PATCH',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(updateBody)
      });

      if (!res.ok) {
        const err = await res.text();
        message = `❌ 물품 테이블 갱신 실패: ${err}`;
        return;
      }

      bidAmount = '';
      memberId = '';
      message = `💰 "${title}"에 ₩${currentBid.toLocaleString()} 입찰 완료!`;
    } catch (err) {
      console.error(err);
      message = '⚠️ 입찰 중 서버 오류가 발생했습니다.';
    }
  }
</script>

<main class="bid-container">
  <h1>{title} – {artist} 입찰 페이지</h1>

  <img src={imageUrl} alt={title} class="detail-image" />

  <div class="info">
    <p>🔹 시작 가격: ₩{startingPrice.toLocaleString()}</p>
    <p>🔹 경매 기간: {new Date(auctionStart).toLocaleString()} ~ {new Date(auctionEnd).toLocaleString()}</p>
    <p>🔹 판매자 회원 닉네임: {ownerId ?? '정보 없음'}</p>
    <p>🔹 현재 최고 입찰자 닉네임: {highestBidderId ?? '없음'}</p>
  </div>

  {#if auctionClosed}
    <p class="final-price">
      🎉 최종 낙찰가: {currentBid > 0 ? `₩${currentBid.toLocaleString()}` : '없음'}
    </p>
  {:else}
    <div class="bid-form">
      <label for="memberId">회원 닉네임 (입찰자)</label>
      <input
        id="memberId"
        type="text"
        bind:value={memberId}
        placeholder="예: kimji01"
      />

      <label for="bid">입찰 금액 (₩)</label>
      <input
        id="bid"
        type="number"
        bind:value={bidAmount}
        placeholder="예: 500000"
        min="1"
      />

      <p>🔹 현재 입찰가: ₩{currentBid.toLocaleString()}</p>
      <button on:click={submitBid}>입찰하기</button>
    </div>
  {/if}

  {#if message}
    <p class="message">{message}</p>
  {/if}
</main>

<style>
  .bid-container {
    max-width: 600px;
    margin: 40px auto;
    padding: 30px;
    background: #fff;
    border-radius: 12px;
    box-shadow: 0 6px 18px rgba(0, 0, 0, 0.08);
    text-align: center;
  }
  .detail-image {
    width: 100%;
    max-width: 400px;
    height: auto;
    margin-bottom: 20px;
    border-radius: 8px;
    object-fit: cover;
    box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
  }
  h1 {
    font-size: 1.8rem;
    margin-bottom: 15px;
    color: #2c3e50;
  }
  .info p {
    font-size: 1.1rem;
    margin: 8px 0;
    color: #34495e;
  }
  .bid-form {
    margin-top: 25px;
    display: flex;
    flex-direction: column;
    gap: 15px;
    align-items: center;
  }
  input {
    padding: 12px;
    width: 80%;
    font-size: 1rem;
    border: 1px solid #ccc;
    border-radius: 8px;
  }
  button {
    background-color: #2c3e50;
    color: white;
    border: none;
    padding: 12px 20px;
    border-radius: 8px;
    font-size: 1rem;
    cursor: pointer;
  }
  button:hover {
    background-color: #1a2a3a;
  }
  .message {
    margin-top: 20px;
    font-weight: 600;
    color: #27ae60;
  }
  .final-price {
    margin-top: 30px;
    font-size: 1.2rem;
    font-weight: bold;
    color: #e74c3c;
  }
</style>
