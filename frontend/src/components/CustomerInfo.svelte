<script>
    export let customer;
</script>

<div class="max-w-sm mx-auto bg-white shadow-md rounded-xl p-6 mt-6">
    <h2 class="text-xl font-semibold text-gray-800 mb-4">Product Info</h2>
    <div class="text-gray-700">
        <p><span class="font-medium">Name:</span> {customer.name}</p>
        <p><span class="font-medium">Email:</span> {customer.email}</p>
        <p><span class="font-medium">Cellphone:</span> {customer.cellphone}</p>
    </div>
</div>



<!-- name: {customer.name}<br/>
email:{customer.email}<br/>
cellphone:{customer.cellphone}<br/> -->

