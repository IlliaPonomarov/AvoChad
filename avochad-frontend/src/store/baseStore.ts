import { defineStore } from 'pinia'
import { UserInterface, ChatInterface } from './types'

class TempStore {
  users: UserInterface[] = [
    {
      id: 1,
      firstName: 'John',
      lastName: 'Doe',
      avatar: {
        id: 1,
        path: 'https://randomuser.me/api/portraits/men/1.jpg'
      }
    },
    {
      id: 2,
      firstName: 'Jane',
      lastName: 'Doe',
      avatar: {
        id: 2,
        path: 'https://randomuser.me/api/portraits/men/2.jpg'
      }
    },
    {
      id: 3,
      firstName: 'Jack',
      lastName: 'Doe',
      avatar: {
        id: 3,
        path: 'https://randomuser.me/api/portraits/men/3.jpg'
      }
    },
    {
      id: 4,
      firstName: 'Jill',
      lastName: 'Doe',
      avatar: {
        id: 4,
        path: 'https://randomuser.me/api/portraits/men/4.jpg'
      }
    }
  ]

  chats: ChatInterface[] = [
    {
      id: 1,
      name: 'Test Chat 1',
      avatar: {
        id: 1,
        path: 'https://randomuser.me/api/portraits/men/48.jpg'
      },
      messages: [
        {
          id: 1,
          text: 'Hello World1' as string,
          time: '2021-01-01 12:00:00',
          from: this.users[0]
        },
        {
          id: 2,
          text: 'Hello World2' as string,
          time: '2021-01-01 12:00:00',
          from: this.users[1]
        },
        {
          id: 3,
          text: 'Hello World3' as string,
          time: '2021-01-01 12:00:00',
          from: this.users[2]
        }
      ],
      users: this.users,
      description: 'Test Chat 1 Description'
    },
    {
      id: 2,
      name: 'Test Chat 2',
      avatar: {
        id: 2,
        path: 'https://randomuser.me/api/portraits/men/10.jpg'
      },
      messages: [
        {
          id: 1,
          text: 'Hello World4' as string,
          time: '2021-01-01 12:00:00',
          from: this.users[0]
        },
        {
          id: 2,
          text: 'Hello World5' as string,
          time: '2021-01-01 12:00:00',
          from: this.users[1]
        },
        {
          id: 3,
          text: 'Hello World6' as string,
          time: '2021-01-01 12:00:00',
          from: this.users[2]
        }
      ],
      users: this.users,
      description: 'Test Chat 2 Description'
    }
  ]
}

export const useChatStore = defineStore({
  id: 'chat',
  state: () => ({
    chats: new TempStore().chats,
    users: new TempStore().users,
    currentChat: null as ChatInterface | null,
    authorizedUser: null as UserInterface | null,
    chatInfo: false as boolean
  }),
  getters: {
    getChats: (state) => {
      return state.chats
    },
    getCurrentChat: (state) => {
      return state.currentChat || state.chats[0]
    },
    getAuthorizedUser: (state) => {
      return state.authorizedUser || state.users[0]
    },
    getUsers: (state) => {
      return state.users
    },
    getChatInfo: (state) => {
      return state.chatInfo
    }
  },
  actions: {
    addChat (chat: ChatInterface) {
      this.chats.push(chat)
    },
    addMessageToCurrentChat (message: string) {
      this.getCurrentChat.messages.push({
        id: this.getCurrentChat.messages.length + 1,
        text: message,
        time: new Date().toISOString(),
        from: this.authorizedUser || this.users[0]
      })
    },
    setCurrentChat (chatId: number) {
      this.currentChat = this.chats.find((chat) => chat.id === chatId) || null
    },
    setAuthorizedUser (userId: number, firstName: string, lastName: string) {
      this.authorizedUser = {
        id: userId,
        firstName,
        lastName,
        avatar: {
          id: 1,
          path: 'https://randomuser.me/api/portraits/men/11.jpg'
        }
      } as UserInterface
    },
    setChatInfo (value: boolean) {
      this.chatInfo = value
    },
    clearCurrentChat () {
      this.getCurrentChat.messages = []
    },
    logout () {
      this.authorizedUser = null
    }
  }
})
