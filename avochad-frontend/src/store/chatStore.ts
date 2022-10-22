import { defineStore } from 'pinia'
import { loginStore } from "src/store/module-example/authStore"
import { ref } from "vue"

export const CURRENT_AUTHORIZED_USER = 'authorizedUser'

interface MainState {
  authorizedUser: object
}

interface ChatInterface {
  id: number
  title: string
  avatar: string
  lastMessage: string
  time: string
  sent: boolean
  stats: boolean
}

export const useMainStore = defineStore('main', {

  state: () => {
    return {
      authorizedUser: {
        email: 'hello',
        password: ''
      },
      currentConversationIndex: 0,
      chats: [
        {
          id: 1,
          title: 'Razvan Stoenescu',
          avatar: 'https://cdn.quasar.dev/team/razvan_stoenescu.jpeg',
          lastMessage: 'I\'m working on Quasar!',
          time: '15:00',
          sent: true,
          stats: true
        },
        {
          id: 2,
          title: 'Dan Popescu',
          avatar: 'https://cdn.quasar.dev/team/dan_popescu.jpg',
          lastMessage: 'I\'m working on Quasar!',
          time: '16:00',
          sent: true,
          stats: true
        },
        {
          id: 3,
          title: 'Jeff Galbraith',
          avatar: 'https://cdn.quasar.dev/team/jeff_galbraith.jpg',
          lastMessage: '',
          time: '18:00',
          sent: true,
          stats: false
        },
        {
          id: 4,
          title: 'Allan Gaunt',
          avatar: 'https://cdn.quasar.dev/team/allan_gaunt.png',
          lastMessage: 'I\'m working on Quasar!',
          time: '17:00',
          sent: true,
          stats: false
        }
      ],
      users: [
        {
          id: 1,
          username: 'Illia',
          avatar: 'https://cdn.quasar.dev/team/dan_popescu.jpg',
          stats: false,
          time: '23.00',
          sent: true
        },
        {
          id: 2,
          username: 'Igor',
          avatar: 'https://cdn.quasar.dev/team/dan_popescu.jpg',
          stats: false,
          time: '3.00',
          sent: true
        },
        {
          id: 3,
          username: 'Gosha',
          avatar: 'https://cdn.quasar.dev/team/dan_popescu.jpg',
          stats: false,
          time: '7.00',
          sent: true
        }
      ]
    }
  },
  getters: {
    getChats: (state) => state.chats,
    getUsers: (state) => state.users
  },
  actions: {
    addChats (user: ChatInterface) {
      this.chats.push(user)
    }
  }

})
export default useMainStore
