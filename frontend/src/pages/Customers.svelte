<script>
    import CustomerInfo from '../components/CustomerInfo.svelte';
    import { customers } from '../stores/index.js';
</script>

<!-- 회원 목록 표시 -->
{#each $customers as customer}
    <CustomerInfo customer={customer} />
    <hr />
{/each}

<style>
    :global(body) {
        font-family: 'Sunflower', sans-serif;
        margin: 0;
        padding: 0;
    }
</style>
