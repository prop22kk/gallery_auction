<script lang="ts">
  import { page } from '$app/stores';

  let title = decodeURIComponent($page.params.title);

  let bidAmount = '';
  let currentBid = 0;
  let message = '';

  // 입찰 버튼 클릭 시 동작
  function submitBid() {
    const bid = parseInt(bidAmount);

    if (!bidAmount || isNaN(bid) || bid <= 0) {
      message = '⚠ 유효한 금액을 입력하세요.';
      return;
    }

    if (bid <= currentBid) {
      message = `⚠ 현재 입찰가보다 높은 금액을 입력해야 합니다.`;
      return;
    }

    currentBid = bid;
    message = `✅ ${title} 작품에 ₩${bid.toLocaleString()} 입찰 완료!`;
    bidAmount = '';
  }
</script>

<main class="bid-container">
  <h1>{title} 입찰 페이지</h1>

  <div class="current-price">
    현재 입찰가: {currentBid > 0 ? `₩${currentBid.toLocaleString()}` : '아직 없음'}
  </div>

  <div class="bid-form">
    <label for="bid">입찰 금액 (₩)</label>
    <input
      id="bid"
      type="number"
      bind:value={bidAmount}
      placeholder="예: 500000"
      min="1"
    />
    <button on:click={submitBid}>입찰하기</button>
  </div>

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

  h1 {
    font-size: 1.8rem;
    margin-bottom: 15px;
    color: #2c3e50;
  }

  .current-price {
    font-size: 1.2rem;
    font-weight: 600;
    margin-bottom: 25px;
    color: #34495e;
  }

  .bid-form {
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
</style>
