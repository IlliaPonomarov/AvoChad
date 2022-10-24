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
      <q-footer class="q-pa-md bg-grey-10 text-white">
        <q-list v-if="recommendedCommands.length > 0" dense bordered padding class="rounded-borders">
          <q-item v-for="(command, index) in recommendedCommands" :key="index" clickable v-ripple>
            <q-item-section @click="text = command">
              {{ command }}
            </q-item-section>
          </q-item>
        </q-list>
        <q-input dark color="white" v-model="text" maxlength="6000" :dense="dense"
          @keyup.enter="sendMessage" @keydown="processInputFill"
          @update:model-value="() => getRecommendedCommands()">
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
import { StringDecoder } from 'string_decoder'
import { ProxyTarget } from 'http-proxy'

export default defineComponent({
  name: 'MainPage',
  methods: {
    sendMessage () {
      if (this.text === '') { return }
      if (!this.detectChatCommand()) { this.store.addMessageToCurrentChat(this.text) }
      this.text = ''
      this.recommendedCommands = []
    },
    windowSize () {
      console.log(window.innerWidth)
      return window.innerWidth
    },
    detectChatCommand () : boolean {
      for (let i = 0; i < this.commands.length; i++) {
        if (this.text === this.commands[i][0]) {
          this.commands[i][1](this.store)
          return true
        }
      }
      return false
    },
    completeCommand () {
      if (this.recommendedCommands.length === 0) { return }
      for (let i = 0; i < this.recommendedCommands.length; i++) {
        if (this.recommendedCommands[i] === this.text) { continue }
        this.text = this.recommendedCommands[i]
        return
      }
    },
    processInputFill (e: KeyboardEvent) {
      if (e.keyCode === 9) { this.completeCommand() }
    },
    getRecommendedCommands () {
      if (this.text === '') {
        this.recommendedCommands = []
        return
      }
      this.recommendedCommands = this.commands.map(command => command[0]).filter(command => command.startsWith(this.text))
    }
  },
  data () {
    return {
      commands: [
        [
          '/clear',
          (store: any) => store.clearCurrentChat()
        ],
        [
          '/new_chat',
          (store: any) => this.$router.push('/create/chat')
        ],
        [
          '/list',
          (store: any) => store.setChatInfo(true)
        ]
      ] as Array<[string, (store: any) => void]>,
      recommendedCommands: [] as string[],
      store: useChatStore(),
      text: '' as string,
      dense: false as boolean
    }
  }
})
</script>

<style scoped>

</style>
