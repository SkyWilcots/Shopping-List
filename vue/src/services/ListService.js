import axios from 'axios';



export default {
    getListsByGroup(groupId) {
        return axios.get(`/lists/${groupId}`)
    },

    createNewList(groupId, listName) {
        return axios.post(`/lists/${groupId}/addlist?listName=${listName}`);
    },

    getItemCount(listId) {
        return axios.get(`lists/test/?listId=${listId}`)
    }

}