<template>
  <router-view />
</template>

<script lang="ts">
import { defineComponent } from 'vue'
import { useQuasar } from 'quasar'
import { useStore } from "src/store"
import { createPinia } from "pinia"

export default defineComponent({
  name: 'App',
  setup () {
    const $q = useQuasar()
    const $store = useStore()
    const pinia = createPinia()
    $q.dark.set(true)
  }
})
</script>
