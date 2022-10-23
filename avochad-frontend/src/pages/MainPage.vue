<template>
  <q-page class="q-pa-md">
    <q-scroll-area
      dark
      class="q-pa-md column col justify-end absolute-bottom"
      style="height: 100%; width: 100%;"
    >
    <!-- <div class="q-pa-md column col justify-end absolute-bottom"> -->
      <div v-for="message in store.getCurrentChat?.messages" :key="message.id">
        <q-chat-message v-if="message.from.id === store.getAuthorizedUser.id && windowSize() > 800" :text="[message.text]" text-color="black" bg-color="light-blue-12">
          <template v-slot:name> {{ message.from.firstName }} </template>
          <template v-slot:stamp>{{ message.time }}</template>
          <template v-slot:avatar>
            <img class="q-message-avatar q-message-avatar--received"
              :src="message.from.avatar.path">
          </template>
        </q-chat-message>

        <q-chat-message v-else-if="message.from.id === store.getAuthorizedUser.id" :text="[message.text]" text-color="black" bg-color="light-blue-12" sent>
          <template v-slot:name> {{ message.from.firstName }} </template>
          <template v-slot:stamp>{{ message.time }}</template>
          <template v-slot:avatar>
            <img class="q-message-avatar q-message-avatar--sent"
              :src="message.from.avatar.path">
          </template>
        </q-chat-message>

        <q-chat-message v-else :text="[message.text]" bg-color="light-blue-11">
          <template v-slot:name> {{ message.from.firstName }} </template>
          <template v-slot:avatar>
            <img class="q-message-avatar q-message-avatar--received"
              :src="message.from.avatar.path">
          </template>
        </q-chat-message>
      </div>
    <!-- </div> -->
    </q-scroll-area>
    <q-footer class="bg-grey-10">
      <q-input square standout color="white" v-model="text" label="write message ..." maxlength="6000" :dense="dense"
        @keyup.enter="sendMessage">
        <template v-slot:append>
          <q-icon v-if="text !== ''" name="close" @click="text = ''" class="cursor-pointer" />
          <q-btn round dense flat icon="send" @click="sendMessage" />
        </template>
      </q-input>
    </q-footer>
  </q-page>
</template>

<script lang="ts">
import { useChatStore } from '../store/baseStore'
import { ref, defineComponent } from 'vue'

export default defineComponent({
  name: 'MainPage',
  methods: {
    sendMessage () {
      if (this.text === '') { return }
      this.store.addMessageToCurrentChat(this.text)
      this.text = ''
    },
    windowSize () {
      console.log(window.innerWidth)
      return window.innerWidth
    }
  },

  data () {
    return {
      store: useChatStore(),
      text: '' as string,
      dense: false as boolean
    }
  }
})
</script>

<style scoped>

</style>
