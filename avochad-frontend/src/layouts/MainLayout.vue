<template>
      <q-layout view="lHh lpR lFf">

    <q-header bordered class="bg-black text-white">
      <div class="row">
        <div class="column">
          <q-toolbar>
            <q-btn dense flat round icon="menu" @click="toggleLeftDrawer" />

            <q-btn round flat>
              <q-avatar>
                <img :src="currentConversation.avatar">
              </q-avatar>
            </q-btn>

            <q-toolbar-title>
              {{ currentConversation.title }}
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

          <div> {{ store.authorizedUser.email }}
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
          <q-item v-for="(chat, index) in filteredUserList()" :key="chat.id" clickable v-ripple @click="setCurrentConversation(index)" >
              <q-item-section avatar>
                <q-avatar>
                  <img :src="chat.avatar">
                </q-avatar>
              </q-item-section>

              <q-item-section>
                <q-item-label> {{ chat.title }} </q-item-label>

                <div v-if="chat.lastMessage === ''">
                  <q-item-label caption lines="1">empty chat</q-item-label>
                </div>
                <div v-else>
                  <q-item-label caption lines="1"> {{ chat.lastMessage }} </q-item-label>
                </div>

                <div v-if="chat.stats">
                  <q-item-label class="items-end justify-end q-mr-lg" style="color: #005adf">
                    <q-chip outline color="teal" text-color="white">
                      online
                    </q-chip>
                  </q-item-label>
                </div>
                <div v-else>
                  <q-item-label class="items-end justify-end q-mr-lg" style="color: #005adf">
                    <q-chip outline color="grey" text-color="white">
                      offline
                    </q-chip>
                  </q-item-label>
                </div>

              </q-item-section>
          </q-item>

        </q-list>
      </q-scroll-area>
<!--      <q-scroll-area>-->
<!--        <q-list bordered>-->
<!--          <q-item v-for="chat in chats" :key="chat.id" class="q-mb-sm" clickable v-ripple>-->
<!--          </q-item>-->
<!--        </q-list>-->
<!--      </q-scroll-area>-->

    </q-drawer>
    <q-page-container>
      <router-view />
    </q-page-container>

  </q-layout>
</template>

<script lang="ts">
import { reactive, ref, computed } from "vue"
import useMainStore from "src/store/chatStore"
import { useRouter } from "vue-router"
import { useQuasar } from "quasar"

export default {
  name: "ChatLayout",
  setup: function () {
    // const leftDrawerOpen = ref(false)
    const leftDrawerOpen = ref(true)
    const currentConversationIndex = ref(0)
    const store = useMainStore()
    const search = ref("")
    const router = useRouter()
    const $q = useQuasar()
    localStorage.setItem("conversation_id", '0')

    const currentConversation = computed(() => {
      return store.chats[store.currentConversationIndex]
    })

    function setCurrentConversation (index: number) {
      currentConversationIndex.value = index
      store.currentConversationIndex = currentConversationIndex.value
      localStorage.setItem("conversation_id", currentConversationIndex.value.toString())
      console.log(store.currentConversationIndex)
    }

    function logout () {
      store.authorizedUser = {
        email: '',
        password: ''
      }
      router.push("/login")
    }

    function filteredUserList () {
      return store.getChats.filter((chat: any) => chat.title.toLowerCase().includes(search.value.toLowerCase()))
    }

    return {
      leftDrawerOpen,
      store,
      logout,
      currentConversation,
      search,
      filteredUserList,
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
