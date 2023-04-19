<template>
  <div class="create-list-form">
    <form class="content" v-on:submit.prevent>
      <div class="field">
        <label class="header" for="Name">Please enter a list name:</label>
        <input type="text" class="form-control" v-model="list.listName" />
      </div>
      <div class="actions">
        <button
          class="create-list"
          type="submit"
          v-on:click.prevent="saveList()"
        >
          Create list
        </button>
      </div>
    </form>
  </div>
</template>

<script>
import listService from "../services/ListService.js";

export default {
  name: "create-list",
  props: ["groupId"],
  data() {
    return {
      list: {
        listId: "",
        listName: "",
        numOfItems: "",
        groupId: "",
      },
    };
  },
  methods: {
    saveList() {
      const listName = this.list.listName;
      listService.createNewList(this.groupId, listName).then((response) => {
        if (response.status === 201) {
          this.$router.push({
            name: "lists",
            params: { groupId: this.groupId },
          });
        }
      });
    },
  },
  computed: {
    currentGroup() {
      return this.list.groupId;
    },
  },
};
</script>

<style>
@import url("https://fonts.googleapis.com/css?family=Raleway:400,700");

.content {
  background: rgba(255, 255, 255, 0.911);
  padding-bottom: 100px;
  width: 760px;
  height: 600px;
  box-shadow: 0px 0px 10px black;
  /* display: flex;
  flex-direction: column;
  align-content: stretch; */
}
.field {
  padding-top: 250px;
  /* align-items: center; */
  margin: auto;
  /* text-align: center; */
}
.header {
  padding-bottom: 100px;
  font-size: 30px;
}
.create-list {
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
  text-transform: uppercase;
  font-weight: 700;
  align-items: center;
  width: 50%;
  color: black;
  box-shadow: 0px 2px 2px black;
  cursor: pointer;
  transition: 0.2s;
}
.create-list:active,
.create-list:focus,
.create-list.hover {
  border-color: #6a679e;
  outline: none;
}
/* .content {
  padding-bottom: 50px;
} */

/* .actions {
  align-items: center;
  text-align: center;
} */
</style>