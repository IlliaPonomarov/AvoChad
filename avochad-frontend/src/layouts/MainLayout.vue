<template>
  <q-layout view="lHh lpR lFf">

    <q-header bordered class="bg-black text-white">
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

      </q-toolbar>
    </q-header>

    <q-drawer show-if-above v-model="leftDrawerOpen" side="left" bordered>
    <div class="column">
      <q-img class="absolute-top" src="icons/grey-background.jpg" style="height: 150px">
        <div class="absolute-bottom bg-transparent">
          <q-avatar size="56px" class="q-mb-sm">
            <img src="icons/pepe-frog.png">
          </q-avatar>
          <div class="text-weight-bold">Razvan Stoenescu</div>
          <div> {{ store.authorizedUser.email }}</div>
        </div>
      </q-img>
    </div>
      <q-scroll-area style="height: calc(100% - 180px); margin-top: 150px; border-right: 1px solid #ddd">
        <q-input
          v-model="search"
          square filled
          placeholder="Search"
          color="white"
        >
          <template v-slot:append>
            <q-icon name="search" />
          </template>
        </q-input>
        <q-list padding>
          <q-item v-for="(chat, index) in store.chats" :key="chat.id" clickable v-ripple @click="setCurrentConversation(index)" >
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
      <div class="q-pb-md absolute-bottom row justify-end items-end">
        <div style="width: 100%; max-width: 930px">
          <q-chat-message
            :text="['Have you seen Quasar?']"
            sent
            text-color="white"
            bg-color="primary"
          >
            <template v-slot:name>me</template>
            <template v-slot:stamp>7 minutes ago</template>
            <template v-slot:avatar>
              <img
                class="q-message-avatar q-message-avatar--sent"
                src="https://cdn.quasar.dev/img/avatar4.jpg"
              >
            </template>
          </q-chat-message>

          <q-chat-message
            bg-color="amber"
          >
            <template v-slot:name>Mary</template>
            <template v-slot:avatar>
              <img
                class="q-message-avatar q-message-avatar--received"
                :src="currentConversation.avatar"
              >
            </template>

            <div>
              Already building an app with it...
            </div>

            <q-spinner-dots size="2rem" />
          </q-chat-message>
        </div>
      </div>
      <q-footer class="bg-grey-10">
        <q-input square standout color="white" v-model="text" label="write message ..."  maxlength="6000" :dense="dense">
          <template v-slot:append>
            <q-icon v-if="text !== ''" name="close" @click="text = ''" class="cursor-pointer" />
            <q-btn round dense flat icon="send" />
          </template>
        </q-input>
      </q-footer>
      <router-view />
    </q-page-container>

  </q-layout>
</template>

<script lang="ts">
import { reactive, ref, computed } from "vue"
import { useStore } from 'vuex'
import useMainStore from "src/store/chatStore"

export default {
  name: "ChatLayout",

  setup () {
    // const leftDrawerOpen = ref(false)
    const leftDrawerOpen = ref(true)
    const currentConversationIndex = ref(0)
    const store = useMainStore()

    const currentConversation = computed(() => {
      return store.chats[currentConversationIndex.value]
    })

    function setCurrentConversation (index: number) {
      currentConversationIndex.value = index
    }

    return {
      leftDrawerOpen,
      store,
      currentConversation,
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
