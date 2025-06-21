<script>
  import { onMount } from 'svelte';

  let customers = [];

  onMount(async () => {
    const response = await fetch('http://localhost:8080/api/customerstest');
    if (response.ok) {
      customers = await response.json();
    } else {
      console.error("고객 데이터를 가져오는데 실패했습니다.");
    }
  });
</script>

<main class="member-list-container">
  <h1>회원 목록</h1>
  <p>아래는 모든 회원의 정보입니다.</p>

  {#if customers.length > 0}
    <table class="member-table">
      <thead>
        <tr>
          <th>ID</th>
          <th>닉네임</th>
          <th>주소</th>
          <th>이메일</th>
          <th>전화번호</th>
        </tr>
      </thead>
      <tbody>
        {#each customers as customer}
          <tr>
            <td>{customer.id}</td>
            <td>{customer.customer_name}</td>
            <td>{customer.address}</td>
            <td>{customer.email}</td>
            <td>{customer.phone_number}</td>
          </tr>
        {/each}
      </tbody>
    </table>
  {:else}
    <p class="no-data">등록된 회원이 없습니다.</p>
  {/if}
</main>

<style>
  .member-list-container {
    max-width: 1000px;
    margin: 40px auto;
    padding: 30px;
    background: #fff;
    border-radius: 12px;
    box-shadow: 0 8px 20px rgba(0, 0, 0, 0.08);
  }

  h1 {
    text-align: center;
    font-size: 2.5rem;
    margin-bottom: 10px;
    color: #2c3e50;
  }

  p {
    text-align: center;
    font-size: 1.2rem;
    color: #555;
    margin-bottom: 30px;
  }

  .no-data {
    text-align: center;
    font-size: 1.1rem;
    color: #999;
    margin-top: 20px;
  }

  .member-table {
    width: 100%;
    border-collapse: collapse;
  }

  .member-table th,
  .member-table td {
    border: 1px solid #ddd;
    padding: 12px;
    text-align: center;
  }

  .member-table th {
    background-color: #f4f4f4;
    font-weight: bold;
    color: #333;
  }

  .member-table tr:hover {
    background-color: #fafafa;
  }
</style>