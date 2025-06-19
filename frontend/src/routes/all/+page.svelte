<script lang="ts">
  import { onMount } from 'svelte';

  type Activity = {
    activityId: number;
    memberId: number;
    itemId: number;
    price: number;
    activityTime: string;
  };

  let activities: Activity[] = [];
  let message = '';

  onMount(async () => {
    try {
      const res = await fetch('http://localhost:8080/api/activities');
      if (res.ok) {
        activities = await res.json();
        if (activities.length === 0) {
          message = '활동 내역이 없습니다.';
        }
      } else {
        message = '활동 데이터를 불러오는 데 실패했습니다.';
      }
    } catch (err) {
      message = '서버 오류가 발생했습니다.';
    }
  });
</script>

<main class="activity-container">
  <h1>📋 전체 고객 활동 조회</h1>
  <p>모든 고객의 최근 활동 내역을 확인하세요.</p>

  {#if message}
    <p class="error-msg">{message}</p>
  {:else if activities.length > 0}
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
    max-width: 1000px;
    margin: 40px auto;
    padding: 30px;
    background: #fff;
    border-radius: 12px;
    box-shadow: 0 8px 20px rgba(0, 0, 0, 0.08);
  }

  h1 {
    text-align: center;
    font-size: 2rem;
    margin-bottom: 10px;
    color: #2c3e50;
  }

  p {
    text-align: center;
    font-size: 1.1rem;
    margin-bottom: 20px;
    color: #555;
  }

  .error-msg {
    color: #e74c3c;
    text-align: center;
    font-weight: 600;
  }

  .activity-table {
    width: 100%;
    border-collapse: collapse;
  }

  .activity-table th,
  .activity-table td {
    border: 1px solid #ddd;
    padding: 12px;
    text-align: center;
    font-size: 1rem;
  }

  .activity-table th {
    background-color: #f5f5f5;
    font-weight: bold;
    color: #333;
  }

  .activity-table tr:hover {
    background-color: #fafafa;
  }
</style>
