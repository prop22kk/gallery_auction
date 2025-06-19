<script>
    import "D:\db2025\gallery\src\app.css";
    import { page } from '$app/stores';
    import { goto } from '$app/navigation';
  
    // 홈('/') 페이지는 사이드바 레이아웃을 건너뜁니다
    $: isHome = $page.url.pathname === '/';
  
    // 경로 이동용 함수
    function goTo(path) {
      goto(path);
    }
  </script>
  
  {#if !isHome}
    <!-- 상단 로고 띠지 -->
    <header class="header">
      <a href="/">
        <img src="/GA_image.png" alt="Gallery Auction Logo" class="logo" />
      </a>
    </header>
  
    <!-- 왼쪽 사이드바 + 본문 -->
    <div class="layout">
      <aside class="sidebar">
        <div class="menu-group">
          <h2>카테고리</h2>
          <ul>
            <li><a href="/painting">그림</a></li>
            <li><a href="/artworks">예술품</a></li>
          </ul>
        </div>
        <div class="menu-group">
          <h2>회원</h2>
          <ul>
            <li><button class="link-button" on:click={() => goTo('/register')}>회원 검색 </button></li>
            <li><button class="link-button" on:click={() => goTo('/members')}>회원 목록</button></li>
            <li><a href="/allmembers">회원 검색</a></li>
          </ul>
        </div>
      </aside>
  
      <main class="content">
        <slot />
      </main>
    </div>
  {:else}
    <!-- 홈 페이지는 그냥 콘텐츠만 -->
    <slot />
  {/if}
  
  <style>
    :global(html, body) {
      margin: 0;
      padding: 0;
    }
  
    /* 상단 띠지 */
    .header {
      display: flex;
      justify-content: center;
      align-items: center;
      padding: 5px 0;
      background: rgba(54, 71, 88, 0.8);
      backdrop-filter: blur(4px);
      position: sticky;
      top: 0;
      z-index: 1000;
    }
    .logo {
      height: 80px;
      object-fit: contain;
    }
  
    /* 레이아웃 그리드 */
    .layout {
      display: flex;
      min-height: calc(100vh - 90px); /* 헤더 높이(약 90px) 제외 */
    }
  
    /* 사이드바 */
    .sidebar {
      width: 220px;
      background-color: rgba(0, 31, 63, 0.85);
      color: white;
      padding: 20px;
      box-shadow: 2px 0 6px rgba(0, 0, 0, 0.1);
      font-family: 'Sunflower', sans-serif;
    }
    .menu-group {
      margin-bottom: 30px;
    }
    .menu-group h2 {
      font-size: 18px;
      border-bottom: 1px solid rgba(255, 255, 255, 0.3);
      padding-bottom: 6px;
      margin-bottom: 10px;
    }
    .sidebar ul {
      list-style: none;
      padding: 0;
    }
    .sidebar li {
      margin: 10px 0;
    }
    .sidebar a {
      color: white;
      text-decoration: none;
      font-size: 15px;
      transition: color 0.2s;
    }
    .sidebar a:hover {
      color: #f0f0f0;
    }
  
    /* 버튼을 링크처럼 보이게 하기 위한 클래스 */
    .link-button {
      background: none;
      border: none;
      padding: 0;
      color: white;
      font-size: 15px;
      cursor: pointer;
      text-align: left;
      font-family: inherit;
    }
    .link-button:hover {
      color: #f0f0f0;
    }
  
    /* 본문 콘텐츠 영역 */
    .content {
      flex-grow: 1;
      padding: 30px;
      font-family: 'Sunflower', sans-serif;
    }
  </style>
ㄴㄴ  