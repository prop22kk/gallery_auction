<script lang="ts">
  import { onMount } from 'svelte';
  let customerId = '';
  let customer = null;
  let error = '';

  async function fetchCustomer() {
    try {
      const res = await fetch(`http://localhost:8080/api/customerstest/${customerId}`);
      if (!res.ok) {
        error = '해당 ID의 회원을 찾을 수 없습니다.';
        customer = null;
        return;
      }
      customer = await res.json();
      error = '';
    } catch (err) {
      error = '서버 오류가 발생했습니다.';
    }
  }
</script>

<main class="search-container">
  <h2>🔍 회원 ID로 검색</h2>
  <div class="search-box">
    <input bind:value={customerId} placeholder="회원 ID를 입력하세요" />
    <button on:click={fetchCustomer}>검색</button>
  </div>

  {#if error}
    <p class="error-msg">{error}</p>
  {:else if customer}
    <div class="result-card">
      <p><strong>ID:</strong> {customer.id}</p>
      <p><strong>닉네임:</strong> {customer.customer_name}</p>
      <p><strong>주소:</strong> {customer.address}</p>
      <p><strong>전화번호:</strong> {customer.phone_number}</p>
      <p><strong>이메일:</strong> {customer.email}</p>
    </div>
  {/if}
</main>

<style>
  .search-container {
    max-width: 600px;
    margin: 50px auto;
    padding: 30px;
    background: #fff;
    border-radius: 12px;
    box-shadow: 0 8px 18px rgba(0, 0, 0, 0.08);
    text-align: center;
  }

  h2 {
    font-size: 1.8rem;
    margin-bottom: 20px;
    color: #2c3e50;
  }

  .search-box {
    display: flex;
    gap: 10px;
    justify-content: center;
    margin-bottom: 20px;
  }

  .search-box input {
    padding: 12px;
    font-size: 1rem;
    border: 1px solid #ccc;
    border-radius: 8px;
    width: 250px;
  }

  .search-box button {
    padding: 12px 20px;
    font-size: 1rem;
    background-color: #2c3e50;
    color: white;
    border: none;
    border-radius: 8px;
    cursor: pointer;
    transition: background-color 0.3s ease;
  }

  .search-box button:hover {
    background-color: #1a2a3a;
  }

  .error-msg {
    color: red;
    font-weight: 600;
    margin-top: 10px;
  }

  .result-card {
    margin-top: 20px;
    text-align: left;
    background-color: #f9f9f9;
    padding: 20px;
    border-radius: 10px;
    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
  }

  .result-card p {
    font-size: 1.1rem;
    margin: 8px 0;
    color: #444;
  }

  strong {
    color: #2c3e50;
  }
</style>
