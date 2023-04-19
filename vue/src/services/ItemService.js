import axios from 'axios'

export default {
    getItemsByListId(listId) {
        return axios.get(`/items/${listId}`)
    },

    createNewItem(listId, item) {
        return axios.post(`/items/${listId}/additem`, item)
    },

    /**
     * 
     * @param {number} itemId 
     * @param {numnber} listId
     * @returns {Promise} a promise that resolves to an Axios response object 
     */
    delete(listId, itemId) {
        return axios.delete(`/items/${listId}/removeItem?itemId=${itemId}`);
    }
}