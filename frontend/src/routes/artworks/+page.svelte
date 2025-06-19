<script>
  import { onMount } from 'svelte';
  import { goto } from '$app/navigation';

  let artworkList = [];
  let ongoingList = [];
  let endedList = [];
  let activeTab = 'ongoing';

  function isOngoing(start, end) {
    const now = new Date();
    return new Date(start) <= now && now < new Date(end);
  }

  function isEnded(end) {
    const now = new Date();
    return new Date(end) <= now;
  }

  onMount(async () => {
    try {
      const res = await fetch('http://localhost:8080/api/items');
      const data = await res.json();
      const sculptures = data.filter(item => item.category === '조각상');

      artworkList = sculptures;
      ongoingList = sculptures.filter(item => isOngoing(item.auctionStart, item.auctionEnd));
      endedList = sculptures.filter(item => isEnded(item.auctionEnd));
    } catch (err) {
      console.error('조각상 불러오기 오류:', err);
    }
  });

  function goToDetail(title) {
    const encoded = encodeURIComponent(title);
    goto(`/art/${encoded}`);
  }
</script>

<main>
  <h1>조각상 갤러리</h1>
  <p>경매 상태에 따라 조각상을 확인할 수 있습니다.</p>

  <div class="tabs">
    <button on:click={() => activeTab = 'ongoing'} class:active={activeTab === 'ongoing'}>
      진행 중인 경매
    </button>
    <button on:click={() => activeTab = 'ended'} class:active={activeTab === 'ended'}>
      종료된 경매
    </button>
  </div>

  <div class="gallery">
    {#each (activeTab === 'ongoing' ? ongoingList : endedList) as artwork}
      <div
        class="artwork-card"
        role="button"
        tabindex="0"
        on:click={() => goToDetail(artwork.itemName)}
        on:keydown={(e) => e.key === 'Enter' && goToDetail(artwork.itemName)}
      >
        <img src={`${artwork.imageUrl}`} alt={artwork.itemName} class="art-image" />
        <h2>{artwork.itemName}</h2>
        <p>현재가: {artwork.currentPrice.toLocaleString()}원</p>
      </div>
    {/each}
  </div>
</main>

<style>
  main {
    max-width: 1200px;
    margin: 0 auto;
    padding: 40px 20px;
    text-align: center;
  }

  h1 {
    font-size: 3rem;
    margin-bottom: 10px;
    color: #2c3e50;
  }

  p {
    font-size: 1.2rem;
    margin-bottom: 30px;
    color: #555;
  }

  .tabs {
    display: flex;
    justify-content: center;
    gap: 20px;
    margin-bottom: 40px;
  }

  .tabs button {
    padding: 10px 20px;
    font-size: 1rem;
    border: 2px solid #ccc;
    border-radius: 8px;
    background-color: white;
    cursor: pointer;
    transition: background-color 0.2s, border-color 0.2s;
  }

  .tabs button.active {
    background-color: #2c3e50;
    color: white;
    border-color: #2c3e50;
  }

  .tabs button:hover {
    border-color: #2c3e50;
  }

  .gallery {
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
    gap: 30px;
  }

  .artwork-card {
    background: #fff;
    border-radius: 12px;
    box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
    overflow: hidden;
    width: 260px;
    cursor: pointer;
    transition: transform 0.3s, box-shadow 0.3s;
    display: flex;
    flex-direction: column;
    align-items: center;
    text-align: center;
    outline: none;
    padding-bottom: 10px;
  }

  .artwork-card:hover,
  .artwork-card:focus {
    transform: translateY(-5px);
    box-shadow: 0 8px 25px rgba(0, 0, 0, 0.15);
  }

  .art-image {
    width: 100%;
    height: 250px;
    object-fit: cover;
    object-position: top;
    border-bottom: 1px solid #eee;
    transition: transform 0.3s ease;
  }

  h2 {
    font-size: 1.1rem;
    font-weight: 600;
    color: #333;
    padding: 10px 10px 5px;
  }

  .artwork-card p {
    font-size: 0.9rem;
    color: #666;
    margin: 2px 0;
  }
</style>
