import axios from 'axios';



export default {
    getGroups(username) {
        return axios.get(`/groups/${username}`)
    },


    create(username, groupName) {
        return axios.post(`/addgroup?username=${username}&groupName=${groupName}`, groupName);
    },

    addUserToGroup(group_code) {
        return axios.post(`/adduser?code=${group_code}`)
    }
}