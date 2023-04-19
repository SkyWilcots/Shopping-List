<template>
  <section>
    <table id="listTable">
      <thead>
        <th class="header"></th>
      </thead>
      <tbody>
        <tr
          class="body"
          v-for="list in this.$store.state.lists"
          :key="list.list_id"
        >
          <router-link
            class="list-cards"
            :to="{ name: 'items', params: { listId: list.listId } }"
          >
            <td class="lists">
              <list :list="list" />
            </td>
          </router-link>
        </tr>
      </tbody>
    </table>
  </section>
</template>

<script>
import List from "../components/List.vue";
import ListService from "../services/ListService.js";

export default {
  name: "lists-list",
  props: ["groupId"],
  components: { List },
  data() {
    return {};
  },
  computed: {
    setLists() {
      return this.$store.state.lists;
    },
    currentGroupId() {
      return this.$store.state.lists.groupId;
    },
  },
  methods: {
    listLists() {
      ListService.getListsByGroup(this.groupId).then((response) => {
        this.$store.commit("SET_LISTS", response.data);
      });
    },
  },
  created() {
    this.listLists();
  },
};
</script>

<style scoped>
#listTable {
  display: flex;
  justify-content: center;
  margin: auto;
  padding-top: 20px;
}
.list-cards {
  text-decoration: none;
}
.lists {
  border: 1px solid black;
  border-radius: 10px;
  width: 500px;
  height: 100px;
  padding-top: 10px;
}
.header {
  font-size: 28px;
  justify-content: center;
}
.body {
  justify-content: center;
  /* border: 20px; */
}
</style>