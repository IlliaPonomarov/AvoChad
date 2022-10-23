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
              <img :src="user.avatar.path">
            </q-avatar>
          </q-item-section>

          <q-item-section>
            <q-item-label> {{ user.firstName }} {{ user.lastName }} </q-item-label>

          </q-item-section>
        </q-item>
      </q-list>
  </q-page>
</template>

<script>
import { reactive, ref, computed } from 'vue'
import { useChatStore } from 'src/store/baseStore'
import { useRouter } from 'vue-router'

export default {
  name: 'CreateChatPage',
  setup () {
    const store = useChatStore()
    const search = ref('')
    const router = useRouter()

    function userFilter () {
      return store.getUsers.filter((user) => (user.firstName.toLowerCase().includes(search.value.toLowerCase())) || user.lastName.toLowerCase().includes(search.value.toLowerCase()))
    }

    function addChat (index) {
      const selectedUser = store.getUsers[index]
      const chats = store.getChats
      store.addChat(
        {
          id: chats.length + 1,
          name: selectedUser.firstName + ' ' + selectedUser.lastName,
          avatar: selectedUser.avatar,
          messages: []
        }
      )
      router.push('/chats')
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
