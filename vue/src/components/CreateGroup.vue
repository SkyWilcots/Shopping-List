<template>
  <div class="create-form">
    <form class="content" v-on:submit.prevent>
      <div class="field">
        <label class="prompt" for="Name">Please enter a group name:</label>
        <input type="text" class="form-control" v-model="group.groupName" />
      </div>
      <div class="actions">
        <button
          class="create-group"
          type="submit"
          v-on:click.prevent="saveGroup()"
        >
          Create group
        </button>
      </div>
    </form>
  </div>
</template>

<script>
import groupService from "../services/GroupService";

export default {
  name: "create-group",
  data() {
    return {
      group: {
        groupId: "",
        groupName: "",
        groupCode: "",
      },
    };
  },
  methods: {
    saveGroup() {
      const groupName = this.group.groupName;
      groupService.create(this.currentUsername, groupName).then((response) => {
        if (response.status === 201) {
          this.$router.push({ name: "home" });
        }
      });
    },
  },
  computed: {
    currentUsername() {
      return this.$store.state.user.username;
    },
  },
};
</script>

<style>
@import url("https://fonts.googleapis.com/css?family=Raleway:400,700");

.prompt {
  padding-bottom: 100px;
  font-size: 30px;
}
.content {
  background: rgba(255, 255, 255, 0.911);
  padding-bottom: 100px;
  width: 760px;
  height: 600px;
  box-shadow: 0px 0px 10px black;
}
.field {
  padding-top: 250px;
  margin: auto;
  text-align: center;
}

.create-group {
  background: #fff;
  position: relative;
  justify-content: center;
  margin-right: auto;
  margin-left: auto;
  font-size: 14px;
  margin-top: 20px;
  padding-top: 120px;
  padding: 16px 20px;
  border: 1px solid #d4d3e8;
  text-transform: upp ercase;
  font-weight: 700;
  align-items: center;
  width: 50%;
  color: black;
  box-shadow: 0px 2px 2px black;
  cursor: pointer;
  transition: 0.2s;
}
.create-group:active,
.create-group:focus,
.create-group.hover {
  border-color: #6a679e;
  outline: none;
}
</style>