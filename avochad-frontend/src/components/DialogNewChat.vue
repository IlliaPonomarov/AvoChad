<template>
  <div>
    <q-btn label="NewChat" icon="keyboard_arrow_up" color="grey" @click="alert = true" />
    <q-dialog v-model="alert">
      <q-card style="width: 350px">
        <q-linear-progress :value="0.6" color="pink" />

        <q-card-section class="row items-center no-wrap">
          <div>
            <div class="text-weight-bold">The Walker</div>
            <div class="text-grey">Fitz & The Tantrums</div>
          </div>
5
          <q-space />

          <q-btn flat round icon="fast_rewind" />
          <q-btn flat round icon="pause" />
          <q-btn flat round icon="fast_forward" />
        </q-card-section>
      </q-card>
    </q-dialog>
  </div>
</template>

<script>
import { ref } from "vue"

export default {
  name: "DialogNewChat",

  setup () {
    const alert = ref(false)

    return {
      alert
    }
  }
}
</script>

<style scoped>

</style>
