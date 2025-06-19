<script lang="ts">
  let memberId = '';
  let activities: { activityTime: string; memberId: number; itemId: number; price: number }[] = [];
  let message = '';

  async function fetchActivities() {
    if (!memberId.trim()) {
      message = '⚠ 회원 ID를 입력해 주세요.';
      activities = [];
      return;
    }

    try {
      const res = await fetch(`http://localhost:8080/api/activities/member/${memberId}`);
      if (res.ok) {
        activities = await res.json();
        message = activities.length === 0 ? '해당 회원의 활동 기록이 없습니다.' : '';
      } else {
        message = '❌ 활동 내역을 불러오는 데 실패했습니다.';
        activities = [];
      }
    } catch (err) {
      console.error(err);
      message = '⚠ 서버 오류가 발생했습니다.';
      activities = [];
    }
  }
</script>

<main class="activity-container">
  <h2>👤 회원 활동 조회</h2>
  <div class="search-form">
    <input
      type="text"
      bind:value={memberId}
      placeholder="회원 ID 입력 (숫자)"
    />
    <button on:click={fetchActivities}>조회</button>
  </div>

  {#if message}
    <p class="message">{message}</p>
  {/if}

  {#if activities.length > 0}
    <table class="activity-table">
      <thead>
        <tr>
          <th>활동 시간</th>
          <th>회원 ID</th>
          <th>물품 ID</th>
          <th>입찰 금액 (₩)</th>
        </tr>
      </thead>
      <tbody>
        {#each activities as activity}
          <tr>
            <td>{new Date(activity.activityTime).toLocaleString()}</td>
            <td>{activity.memberId}</td>
            <td>{activity.itemId}</td>
            <td>{activity.price.toLocaleString()}</td>
          </tr>
        {/each}
      </tbody>
    </table>
  {/if}
</main>

<style>
  .activity-container {
    max-width: 900px;
    margin: 40px auto;
    padding: 30px;
    background: #fff;
    border-radius: 12px;
    box-shadow: 0 8px 20px rgba(0, 0, 0, 0.08);
  }

  h2 {
    font-size: 1.8rem;
    margin-bottom: 20px;
    text-align: center;
    color: #2c3e50;
  }

  .search-form {
    display: flex;
    gap: 10px;
    justify-content: center;
    margin-bottom: 20px;
  }

  .search-form input {
    padding: 10px;
    width: 300px;
    border: 1px solid #ccc;
    border-radius: 8px;
    font-size: 1rem;
  }

  .search-form button {
    padding: 10px 16px;
    background-color: #2c3e50;
    color: white;
    border: none;
    border-radius: 8px;
    font-size: 1rem;
    cursor: pointer;
  }

  .search-form button:hover {
    background-color: #1a2a3a;
  }

  .message {
    text-align: center;
    color: #e74c3c;
    margin-top: 10px;
    font-weight: bold;
  }

  .activity-table {
    width: 100%;
    border-collapse: collapse;
    margin-top: 20px;
  }

  .activity-table th,
  .activity-table td {
    border: 1px solid #ddd;
    padding: 12px;
    text-align: center;
  }

  .activity-table th {
    background-color: #f5f5f5;
    font-weight: bold;
  }

  .activity-table tr:hover {
    background-color: #fafafa;
  }
</style>
