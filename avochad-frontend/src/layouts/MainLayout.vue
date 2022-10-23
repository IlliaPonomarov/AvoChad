<template>
      <q-layout view="lHh lpR lFf">

    <q-header bordered class="bg-black text-white">
      <div class="row">
        <div class="column">
          <q-toolbar>
            <q-btn dense flat round icon="menu" @click="toggleLeftDrawer" />

            <q-btn round flat>
              <q-avatar>
                <img :src="store.getCurrentChat?.avatar.path">
              </q-avatar>
            </q-btn>

            <q-toolbar-title>
              {{ store.getCurrentChat?.name }}
            </q-toolbar-title>
            <q-btn round flat icon="more_horiz">
              <q-menu  auto-close :offset="[110, 8]">
                <q-list style="min-width: 150px">
                  <q-item clickable>
                    <q-item-section>Contact data</q-item-section>
                  </q-item>
                  <q-item clickable>
                    <q-item-section>Block</q-item-section>
                  </q-item>
                  <q-item clickable>
                    <q-item-section>Select messages</q-item-section>
                  </q-item>
                  <q-item clickable>
                    <q-item-section>Silence</q-item-section>
                  </q-item>
                  <q-item clickable>
                    <q-item-section>Clear messages</q-item-section>
                  </q-item>
                  <q-item clickable>
                    <q-item-section>Erase messages</q-item-section>
                  </q-item>
                </q-list>
              </q-menu>
            </q-btn>
          </q-toolbar>

        </div>
      </div>
    </q-header>

    <q-drawer show-if-above v-model="leftDrawerOpen" side="left" bordered>
    <div class="column">
      <q-img class="absolute-top" src="icons/grey-background.jpg" style="height: 150px">
        <div class="absolute-bottom bg-transparent">
          <q-avatar size="56px" class="q-mb-sm">
            <img src="icons/pepe-frog.png">
          </q-avatar>
          <div class="text-weight-bold">Razvan Stoenescu</div>

          <div> {{ store.authorizedUser?.firstName }} {{ store.authorizedUser?.lastName }}
            <q-btn round flat icon="more_horiz">
              <q-menu  auto-close :offset="[110, 8]">
                <q-list style="min-width: 150px">
                  <q-item clickable @click="$router.push('/create/chat')">
                    <q-item-label>New chat</q-item-label>
                  </q-item>
                  <q-item clickable>
                    <q-item-section>Settings</q-item-section>
                  </q-item>
                  <q-item clickable @click="logout()">
                    <q-item-section>Logout</q-item-section>
                  </q-item>
                </q-list>
              </q-menu>
            </q-btn>
          </div>

        </div>
      </q-img>
    </div>
    <q-scroll-area style="height: calc(100% - 180px); margin-top: 150px; border-right: 1px solid #ddd">
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
        <q-item v-for="chat in findChats()" :key="chat.id" clickable v-ripple @click="setCurrentConversation(chat.id)" >
            <q-item-section avatar>
              <q-avatar>
                <img :src="chat.avatar.path">
              </q-avatar>
            </q-item-section>

            <q-item-section>
              <q-item-label> {{ chat.name }} </q-item-label>
            </q-item-section>
        </q-item>

      </q-list>
    </q-scroll-area>

    </q-drawer>
    <q-page-container>
      <router-view />
    </q-page-container>

  </q-layout>
</template>

<script lang="ts">
import { reactive, ref, computed } from 'vue'
import { useChatStore } from 'src/store/baseStore'
import { useRouter } from 'vue-router'
import { useQuasar } from 'quasar'

export default {
  name: 'ChatLayout',
  setup: function () {
    // const leftDrawerOpen = ref(false)
    const leftDrawerOpen = ref(true)
    const currentChat = ref(0)
    const store = useChatStore()
    const search = ref('')
    const router = useRouter()
    const $q = useQuasar()
    localStorage.setItem('conversation_id', '0')

    function setCurrentConversation (index: number) {
      currentChat.value = index
      store.setCurrentChat(currentChat.value)
      localStorage.setItem('conversation_id', currentChat.value.toString())
      console.log(store.currentChat)
    }

    function logout () {
      store.logout()
      router.push('/login')
    }

    function findChats () {
      return store.chats.filter((chat) => {
        return chat.name.toLowerCase().includes(search.value.toLowerCase())
      })
    }

    return {
      leftDrawerOpen,
      store,
      logout,
      search,
      findChats,
      setCurrentConversation,
      toggleLeftDrawer () {
        leftDrawerOpen.value = !leftDrawerOpen.value
      }
    }
  }
}
</script>

<style scoped>

</style>
