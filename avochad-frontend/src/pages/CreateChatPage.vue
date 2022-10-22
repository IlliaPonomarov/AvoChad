<template>
  <q-page>
      <q-input
        v-model="search"
        square filled
        placeholder="search ..."
        color="white"
      >
        <template v-slot:append>
          <q-icon name="search" />
        </template>
      </q-input>
      <q-list padding>
        <q-item v-for="(user, index) in userFilter()" :key="user.id" clickable v-ripple @click="addChat(index)" >
          <q-item-section avatar>
            <q-avatar>
              <img :src="user.avatar">
            </q-avatar>
          </q-item-section>

          <q-item-section>
            <q-item-label> {{ user.username }} </q-item-label>

          </q-item-section>
        </q-item>
      </q-list>
  </q-page>
</template>

<script>
import { reactive, ref, computed } from "vue"
import useMainStore from "src/store/chatStore"
import { useRouter } from "vue-router"
import { useQuasar } from "quasar"

export default {
  name: "CreateChatPage",
  setup () {
    const store = useMainStore()
    const search = ref("")
    const userIndex = ref(-1)
    const router = useRouter()
    function userFilter () {
      return store.getUsers.filter((user) => user.username.toLowerCase().includes(search.value.toLowerCase()))
    }

    function addChat (index) {
      const selectedUser = store.getUsers[index]
      const chats = store.getChats
      store.addChats({
        id: chats[chats.length - 1] + 1,
        title: selectedUser.username,
        avatar: selectedUser.avatar,
        lastMessage: '',
        stats: false,
        time: (new Date().getHours()),
        sent: false
      })

      router.push("/chats")
    }
    return {
      store,
      search,
      userFilter,
      addChat
    }
  }
}
</script>

<style scoped>

</style>
