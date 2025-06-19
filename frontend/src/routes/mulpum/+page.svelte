<script lang="ts">
  import { onMount } from 'svelte';

  let newItem = {
    artist: '',
    itemName: '',
    startingPrice: '',
    auctionStart: '',
    auctionEnd: '',
    category: '',
    ownerId: '',
    imageUrl: ''
  };

  let message = '';
  let previewUrl: string | null = null;

  // 현재 시각을 'YYYY-MM-DDTHH:MM' 형식으로 변환
  function getCurrentDateTimeLocal(): string {
    const now = new Date();
    const offset = now.getTimezoneOffset();
    const localDate = new Date(now.getTime() - offset * 60000);
    return localDate.toISOString().slice(0, 16);
  }

  onMount(() => {
    newItem.auctionStart = getCurrentDateTimeLocal(); // 시작일 고정
  });

  function handleImageChange(event: Event) {
    const target = event.target as HTMLInputElement;
    if (target.files && target.files.length > 0) {
      const file = target.files[0];
      previewUrl = URL.createObjectURL(file);
    } else {
      previewUrl = null;
    }
  }

  async function addItem() {
    // 등록 시에도 다시 한 번 현재 시각 고정 (보안적으로 더 안전)
    newItem.auctionStart = getCurrentDateTimeLocal();

    const bodyData = {
      artist: newItem.artist,
      itemName: newItem.itemName,
      startingPrice: parseFloat(newItem.startingPrice),
      auctionStart: newItem.auctionStart,
      auctionEnd: newItem.auctionEnd,
      category: newItem.category,
      ownerId: parseInt(newItem.ownerId),
      imageUrl: newItem.imageUrl.trim()
    };

    try {
      const res = await fetch('http://localhost:8080/api/items', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(bodyData)
      });

      if (res.ok) {
        message = '✅ 물품이 성공적으로 등록되었습니다!';
        newItem = {
          artist: '',
          itemName: '',
          startingPrice: '',
          auctionStart: getCurrentDateTimeLocal(),
          auctionEnd: '',
          category: '',
          ownerId: '',
          imageUrl: ''
        };
        previewUrl = null;
      } else {
        const err = await res.text();
        message = `❌ 물품 등록에 실패했습니다: ${err}`;
      }
    } catch (err) {
      console.error(err);
      message = '⚠️ 서버 오류가 발생했습니다.';
    }
  }
</script>

<main class="register-container">
  <h1>물품 등록</h1>
  <form class="register-form" on:submit|preventDefault={addItem}>
    <div class="form-group">
      <label for="artImage">작품 이미지 (파일 선택)</label>
      <input type="file" id="artImage" accept="image/*" on:change={handleImageChange} />
      {#if previewUrl}
        <img class="preview-image" src={previewUrl} alt="이미지 미리보기" />
      {/if}
    </div>

    <div class="form-group">
      <label for="imageUrl">작품 이미지 URL</label>
      <input
        type="text"
        id="imageUrl"
        bind:value={newItem.imageUrl}
        placeholder="예: /images/art1.jpg"
      />
    </div>

    <div class="form-group">
      <label for="artist">작가명</label>
      <input type="text" id="artist" bind:value={newItem.artist} placeholder="예: 다빈치" required />
    </div>

    <div class="form-group">
      <label for="itemName">작품 제목</label>
      <input type="text" id="itemName" bind:value={newItem.itemName} placeholder="예: 모나리자" required />
    </div>

    <div class="form-group">
      <label for="startingPrice">시작 가격</label>
      <input type="number" id="startingPrice" bind:value={newItem.startingPrice} placeholder="예: 1000000" required />
    </div>

    <div class="form-group">
      <label for="auctionStart">경매 시작일 (현재 시각)</label>
      <input type="datetime-local" id="auctionStart" bind:value={newItem.auctionStart} readonly />
    </div>

    <div class="form-group">
      <label for="auctionEnd">경매 종료일</label>
      <input type="datetime-local" id="auctionEnd" bind:value={newItem.auctionEnd} required />
    </div>

    <div class="form-group">
      <label for="category">카테고리</label>
      <select id="category" bind:value={newItem.category} required>
        <option value="" disabled selected>카테고리를 선택하세요</option>
        <option value="미술품">미술품</option>
        <option value="조각상">조각상</option>
      </select>
    </div>

    <div class="form-group">
      <label for="ownerId">소유자 ID</label>
      <input type="number" id="ownerId" bind:value={newItem.ownerId} placeholder="예: 1" required />
    </div>

    <button type="submit" class="submit-btn">등록하기</button>
    {#if message}
      <p class="message">{message}</p>
    {/if}
  </form>
</main>

<style>
  .register-container {
    max-width: 800px;
    margin: 40px auto;
    padding: 30px;
    background: #fff;
    border-radius: 12px;
    box-shadow: 0 8px 20px rgba(0,0,0,0.08);
  }

  .register-container h1 {
    font-size: 2rem;
    text-align: center;
    color: #2c3e50;
    margin-bottom: 20px;
  }

  .register-form {
    display: flex;
    flex-direction: column;
    gap: 20px;
  }

  .form-group {
    display: flex;
    flex-direction: column;
  }

  label {
    margin-bottom: 6px;
    font-weight: 600;
    color: #333;
  }

  input, select {
    padding: 12px;
    font-size: 1rem;
    border: 1px solid #ccc;
    border-radius: 8px;
    outline: none;
    font-family: inherit;
    box-sizing: border-box;
    background: white;
  }

  input:focus, select:focus {
    border-color: #4a90e2;
  }

  .submit-btn {
    background-color: #2c3e50;
    color: white;
    border: none;
    padding: 14px;
    border-radius: 8px;
    font-size: 1rem;
    cursor: pointer;
    transition: background-color .3s;
  }

  .submit-btn:hover {
    background-color: #1a2a3a;
  }

  .message {
    margin-top: 12px;
    text-align: center;
    font-weight: bold;
    color: #2c3e50;
  }

  .preview-image {
    margin-top: 10px;
    max-width: 300px;
    max-height: 300px;
    border-radius: 10px;
    border: 1px solid #ccc;
    object-fit: contain;
  }
</style>
