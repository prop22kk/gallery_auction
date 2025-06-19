const callApi = async ({ method = '', path = '', data = {} } = {}) => {
    const commonUrl = 'http://localhost:8080/api'
    const url = commonUrl + path

    const headers = {
        "Content-Type": "application/json;charset=UTF-8",
        "Accept": "application/json,",
    }

    const options = {
        method,
        headers,
        ...(method !== 'GET' && { body: JSON.stringify(data) })
    }

    try {
        const response = await fetch(url, options);
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }
        const responseData = await response.json();
        console.log(options)
        console.log(responseData)
        return responseData
    }
    catch (error) {
        throw error
    }
}

const getCustomers = async () => {
    return callApi({method: "GET", path: "/customers"})
}

const addCustomer = async (customer) => {
    console.log(customer)
    return callApi({method: "POST", path: "/customers", data: {...customer}})
}

const updateCustomer = async (customer) => {
    console.log(customer)
    return callApi({method: "PATCH", path: `/customers/${customer.id}`, data: {...customer}})
}

const removeCustomer = async (customer) => {
    console.log(customer)
    return callApi({method: "DELETE", path: `/customers/${customer.id}`})
}

const getAccounts = async (customerId) => {
    return callApi({method: "GET", path: `/customers/${customerId}/accounts`})
}

const getAccountsAll = async () => {
    return callApi({method: "GET", path: "/accounts"})
}

export {
    getCustomers,
    addCustomer,
    updateCustomer,
    removeCustomer,
    getAccounts,
    getAccountsAll,
}
