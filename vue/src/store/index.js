import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

/*
 * The authorization header is set for axios when you login but what happens when you come back or
 * the page is refreshed. When that happens you need to check for the token in local storage and if it
 * exists you should set the header so that it will be attached to each request
 */
const currentToken = localStorage.getItem('token')
const currentUser = JSON.parse(localStorage.getItem('user'));

if (currentToken != null) {
  axios.defaults.headers.common['Authorization'] = `Bearer ${currentToken}`;
}

export default new Vuex.Store({
  state: {
    activeGroup: {
      groupId: 0,
      groupName: "",
      lists: [],
    },
    items: [{
      itemId: "",
      itemName: "",
      quantity: 0,
      listId: "",
      dateAdded: "",
      userId: "",
    }],
    groups: [{
      group_id: "",
      group_name: "",
      admin_id: "",
      group_code: "",
    }],
    lists: [{
      list_id: "",
      list_name: "",
      num_of_items: "",
      groupId: "",
    }],

    token: currentToken || '',
    user: currentUser || {},
  },
  mutations: {
    SET_AUTH_TOKEN(state, token) {
      state.token = token;
      localStorage.setItem('token', token);
      axios.defaults.headers.common['Authorization'] = `Bearer ${token}`
    },
    SET_USER(state, user) {
      state.user = user;
      localStorage.setItem('user', JSON.stringify(user));
    },
    SET_ACTIVE_GROUP(state, data) {
      state.activeGroup = data;
    },
    LOGOUT(state) {
      localStorage.removeItem('token');
      localStorage.removeItem('user');
      state.token = '';
      state.user = {};
      axios.defaults.headers.common = {};
    },
    SET_GROUPS(state, groups) { state.groups = groups; },
    SET_LISTS(state, lists) { state.lists = lists },
    SET_ITEMS(state, items) {
      state.items = items
    },
    DELETE_ITEM(state, itemId) {
      state.items = state.items.filter((item) => {
        return item.itemId !== itemId;
      });

    }
  }
})
