<template>
  <q-page class="q-pa-md">
    <div class="q-pa-md column col justify-end absolute-bottom">

      <div>
        <q-chat-message
          :text="['Have you seen Quasar?']"
          text-color="black"
          bg-color="light-blue-12"
        >
          <template v-slot:name>{{ store.authorizedUser.email }}</template>
          <template v-slot:stamp>7 minutes ago</template>
          <template v-slot:avatar>
            <img
              class="q-message-avatar q-message-avatar--sent"
              src="https://cdn.quasar.dev/img/avatar4.jpg"
            >
          </template>
        </q-chat-message>
      </div>

      <div >
        <q-chat-message
          bg-color="light-blue-11"
        >
          <template v-slot:name> {{ store.getChats[store.currentConversationIndex].title }} </template>
          <template v-slot:avatar>
            <img
              class="q-message-avatar q-message-avatar--received"
              :src="store.getChats[store.currentConversationIndex].avatar"
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
  </q-page>
</template>

<script lang="ts">
import useMainStore from "src/store/chatStore"
import { ref, triggerRef, watch } from "vue"

export default {
  name: "MainPage",
  setup () {
    const store = useMainStore()
    const currentConversation = ref(store.chats[store.currentConversationIndex])
    return {
      store,
      currentConversation
    }
  },

  data () {
    return {
      text: '',
      dense: false
    }
  }
}
</script>

<style scoped>

</style>
