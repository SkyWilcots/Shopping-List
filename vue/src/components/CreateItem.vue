<template>
  <div class="create-item">
    <form class="content-form" v-on:submit.prevent>
      <label class="label1" for="Name"></label>
      <div class="login_field">
        <input
          type="text"
          class="form-control"
          placeholder="Enter Item Here"
          v-model="item.itemName"
        />
      </div>
      <br />

      <label class="label2" for="Quantity"></label>
      <div class="login_field">
        <input
          type="number"
          class="form-control"
          min="0"
          v-model.number="item.quantity"
          placeholder="Enter or select quantity here:"
        />
      </div>

      <div class="actions">
        <button class="add-item" type="submit" v-on:click.prevent="saveItem()">
          Add Item
        </button>
      </div>
    </form>
  </div>
</template>

<script>
import ItemService from "../services/ItemService.js";

export default {
  name: "create-item",
  props: ["listId"],
  data() {
    return {
      item: {
        itemId: "",
        itemName: "",
        // quantity: 0,
        listId: this.listId,
        userId: 0,
      },
    };
  },
  methods: {
    saveItem() {
      //   const itemName = this.item.itemName;
      //   const quantity = this.item.quantity;
      this.item.userId = this.currentUserId;
      ItemService.createNewItem(this.item.listId, this.item).then(
        (response) => {
          if (response.status === 201) {
            this.$router.push({
              name: "items",
              params: { listId: this.item.listId },
            });
          }
        }
      );
    },
  },
  computed: {
    currentList() {
      return this.item.listId;
    },
    currentUserId() {
      return this.$store.state.user.id;
    },
  },
};
</script>

<style>
@import url("https://fonts.googleapis.com/css?family=Raleway:400,700");

.create-item {
  display: flex;
  align-items: center;
  justify-content: center;
  min-height: 100vh;
}

.content-form {
  background: rgba(255, 255, 255, 0.911);
  position: absolute;
  width: 760px;
  padding: 30px;

  margin: auto;
  text-align: center;
  box-shadow: 0px 0px 10px black;
  /* height: 100%; */
  height: 600px;
  display: flex;
  flex-direction: column;
  align-content: stretch;
}
.create-item-form {
  justify-content: center;
}
.login__field {
  padding: 20px 0px;
  position: relative;
}

/* .header {
  padding-bottom: 100px;
  font-size: 30px;
} */
.actions {
  align-items: center;
  text-align: center;
}
.add-item {
  background: #fff;
  position: relative;
  margin-right: auto;
  margin-left: auto;
  font-size: 14px;
  margin-top: 30px;
  padding-top: 150px;
  padding: 16px 20px;
  border: 1px solid #d4d3e8;
  text-transform: uppercase;
  font-weight: 700;
  width: 50%;
  color: black;
  box-shadow: 0px 2px 2px black;
  cursor: pointer;
  transition: 0.2s;
}
.create-item:active,
.create-item:focus,
.create-item.hover {
  border-color: #6a679e;
  outline: none;
}

.form-control {
  border: none;
  border-bottom: 2px solid #d1d1d4;
  background: none;
  text-align: center;
  /* padding: 10px; */
  padding-top: 50px;
  /* padding-left: 24px; */
  font-weight: 700;
  width: 75%;
  transition: 0.2s;
}
/* .label1 {
  position: fixed;
  left: 10px;
  bottom: 510px;
  right: 50px;
}
.label2 {
  position: fixed;
  left: 600px;
  bottom: 430px;
} */
</style>