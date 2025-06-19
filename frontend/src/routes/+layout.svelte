<script>
  import "../app.css";
  import { page } from '$app/stores';
  $: isHome = $page.url.pathname === '/';
</script>

{#if !isHome}
  <!-- 상단 헤더 -->
  <header class="header">
    <a href="/">
      <img src="/GA_image.png" alt="Gallery Auction Logo" class="logo" />
    </a>
  </header>

  <!-- 전체 레이아웃 -->
  <div class="layout">
    <!-- 고정 사이드바 -->
    <aside class="sidebar-fixed">
      <div class="sidebar">
        <nav>
          <div class="menu-group">
            <h2>카테고리</h2>
            <ul>
              <li><a href="/painting">미술품</a></li>
              <li><a href="/artworks">조각상</a></li>
            </ul>
          </div>
          <div class="menu-group">
            <h2>회원</h2>
            <ul>
              <li><a href="/register/new">신규 회원 등록</a></li>
              <li><a href="/members">회원 목록</a></li>
              <li><a href="/search">회원 검색</a></li>
              <li><a href="/del">회원 삭제</a></li>
              <li><a href="/update">회원 정보 수정</a></li>
            </ul>
          </div>
          <div class="menu-group">
            <h2>기타</h2>
            <ul>
              <li><a href="/mulpum">물품등록</a></li>
              <li><a href="/gogag">고객센터</a></li>
              <li><a href="/some">특정고객 활동 조회</a></li>
              <li><a href="/all">모든고객 활동 조회</a></li>
            </ul>
            </div>
        </nav>
      </div>
    </aside>

    <!-- 본문 콘텐츠 -->
    <main class="content">
      <slot />
    </main>
  </div>
{:else}
  <slot />
{/if}

<style>
  @import url('https://fonts.googleapis.com/css2?family=SUIT&display=swap');

  :global(html, body) {
    margin: 0;
    padding: 0;
    font-family: 'SUIT', sans-serif;
    background-color: #f4f7fa;
    color: #1a1a1a;
  }

  .header {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 80px;
    background: #1c2d50;
    border-bottom: 1px solid #13203a;
    position: sticky;
    top: 0;
    z-index: 999;
  }
  .logo {
    height: 60px;
    object-fit: contain;
    transition: transform 0.3s ease;
    filter: drop-shadow(0 2px 4px rgba(0,0,0,0.25));
  }
  .logo:hover {
    transform: scale(1.05);
  }

  .layout {
    display: flex;
    min-height: 100vh;
  }

  .sidebar-fixed {
    position: fixed;
    top: 80px;
    left: 0;
    width: 220px;
    height: calc(100vh - 80px);
    background: #223559;
    border-right: 1px solid #1a2c47;
    box-shadow: 2px 0 10px rgba(0, 0, 0, 0.1);
    z-index: 998;
  }

  .sidebar {
    padding: 30px 20px;
    height: 100%;
    overflow-y: auto;
    color: #e4e9f0;
  }

  .menu-group {
    margin-bottom: 36px;
  }
  .menu-group h2 {
    font-size: 16px;
    color: #a6b3c6;
    margin-bottom: 12px;
    border-bottom: 1px solid #394c6c;
    padding-bottom: 6px;
  }
  .sidebar ul {
    list-style: none;
    padding: 0;
  }
  .sidebar li {
    margin-bottom: 10px;
  }
  .sidebar a {
    color: #d8e0ef;
    text-decoration: none;
    font-size: 15px;
    padding: 8px 10px;
    display: block;
    border-radius: 6px;
    transition: background 0.2s ease, color 0.2s ease;
  }
  .sidebar a:hover {
    background: #2f456f;
    color: #ffffff;
  }

  .content {
    flex-grow: 1;
    padding: 40px;
    margin-left: 220px;
    background: #ffffff;
  }

  @media (max-width: 768px) {
    .layout {
      flex-direction: column;
    }
    .sidebar-fixed {
      position: relative;
      width: 100%;
      height: auto;
      top: unset;
      border-right: none;
      border-bottom: 1px solid #ccc;
    }
    .content {
      margin-left: 0;
      padding: 20px;
    }
  }
</style>
