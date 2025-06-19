import {writable}  from 'svelte/store';
import { getCustomers } from './api.js';

function setCustomers() {
    const {subscribe,set} = writable([]);

    const fetch = async() => {
        const result = await getCustomers();
        console.log(result);
        set(result);
    }

    return{
        subscribe,
        fetch,
    };
}

export const customers = setCustomers();