<template>
<div>
  <q-layout class="hHh lpR fFf">
    <q-header elevated class="bg-black text-white" height-hint="98">

      <q-tabs  align="left">
        <q-route-tab to="/chats" label="Back" />
      </q-tabs>
    </q-header>
    <q-page-container>
      <router-view></router-view>
    </q-page-container>
  </q-layout>
</div>
</template>

<script>
export default {
  name: "CreateChatLayout"
}
</script>

<style scoped>

</style>
