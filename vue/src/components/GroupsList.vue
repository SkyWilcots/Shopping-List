<template>
  <section>
    <table id="groupTable">
      <thead>
        <th class="header"></th>
      </thead>
      <tbody>
        <tr class="body" v-for="group in setGroups" :key="group.groupId">
          <router-link
            class="group-cards"
            :to="{ name: 'lists', params: { groupId: group.groupId } }"
          >
            <td class="groups">
              <group :group="group" style="padding-top: 15px" />
            </td>
          </router-link>
        </tr>
      </tbody>
    </table>
    <button class="join-group-button" v-on:click="showForm = !showForm">
      <!-- how to add additional isHidden = !isHidden v-on:click? -->
      Join Group
    </button>
    <div v-show="showForm">
      <form @submit.prevent="addUserToGroup()">
        <label for="code-input"></label><br />
        <input
          class="group-code-label"
          placeholder="Please provide Group Code here"
          type="text"
          id="code-input"
          name="code-input"
          v-model="codeInput"
        /><br /><br />
        <input class="submit-button" type="submit" value="Submit" />
      </form>
    </div>
  </section>
</template>

<script>
import Group from "../components/Group.vue";
import GroupService from "@/services/GroupService.js";

export default {
  name: "group-list",
  components: { Group },
  data() {
    return {
      codeInput: "",
      showForm: false,
      isHidden: false, //should i delete it?
    };
  },
  computed: {
    setGroups() {
      return this.$store.state.groups;
    },
    currentUsername() {
      return this.$store.state.user.username;
    },
  },
  methods: {
    listGroup() {
      GroupService.getGroups(this.currentUsername).then((response) => {
        this.$store.commit("SET_GROUPS", response.data);
      });
    },
    addUserToGroup() {
      GroupService.addUserToGroup(this.codeInput).then((response) => {
        if (response.status === 202) {
          this.listGroup();
          alert("User added!");
        }
      });
    },
  },
  created() {
    this.listGroup();
  },
};
</script>

<style scoped>
.groups {
  border: 2px solid black;
  border-radius: 10px;
  width: 500px;
  height: 100px;
}
#groupTable {
  display: flex;
  justify-content: center;
  margin: 100px;
}
.header {
  font-size: 28px;
  justify-content: center;
}
.body {
  justify-content: center;
  border: 20px;
}

.group-cards {
  text-decoration: none;
}

.join-group-button {
  justify-content: center;
  background: #fff;
  font-size: 14px;
  margin-top: 30px;
  padding: 16px 20px;
  border: 1px solid #d4d3e8;
  text-transform: uppercase;
  font-weight: 700;
  display: flex;
  align-items: center;
  width: 100%;
  color: black;
  box-shadow: 0px 2px 2px black;
  cursor: pointer;
  transition: 0.2s;
}
.join-group-button:active,
.join-group-button:focus,
.join-group-button:hover {
  border-color: #6a679e;
  outline: none;
}
.group-code-label {
  border: none;
  border-bottom: 2px solid #d1d1d4;
  background: none;
  padding: 10px;
  padding-left: 24px;
  font-weight: 700;
  width: 100%;
  transition: 0.2s;
}
.submit-button {
  justify-content: center;
  background: #fff;
  font-size: 14px;
  margin-top: 30px;
  padding: 16px 20px;
  border: 1px solid #d4d3e8;
  text-transform: uppercase;
  font-weight: 700;
  display: flex;
  align-items: center;
  width: 100%;
  color: black;
  box-shadow: 0px 2px 2px black;
  cursor: pointer;
  transition: 0.2s;
}
.submit-button:active,
.submit-button:focus,
.submit-button:hover {
  border-color: #6a679e;
  outline: none;
}
</style>