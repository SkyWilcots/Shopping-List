<template>
  <div class="group">
    <h1 class="group-name">{{ group.groupName }}</h1>
    <h2 class="date">created on: {{ group.createDate }}</h2>
    <div>
      <!-- <button v-on:click="showInvite = !showInvite">Invite Code</button> -->
    </div>
    <h2 class="invite" v-show="showInvite">
      Invite Code: <strong>{{ group.groupCode }}</strong>
    </h2>
  </div>
</template>

<script>
export default {
  name: "group-card",
  props: ["group"],
  data() {
    return {
      showInvite: true,
    };
  },
  computed: {
    currentUserId() {
      return this.$store.state.user.id;
    },
  },
};
</script>

<style>
.group {
  align-content: center;
  margin: 10px;
}
.date,
.invite {
  font-size: 16px;
  padding-top: 10px;
}

.invite {
  font-size: 10px;
  padding-top: 5px;
}
</style>