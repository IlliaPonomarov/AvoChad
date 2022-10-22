<template>
  <div class="outline-button">
    <q-btn type="{{ btnType }}" text-color="white" outline color="black">{{ btnLabel }}</q-btn>
  </div>
</template>

<script>
export default {
  name: "OutlineButtons",

  props: {
    btnLabel: String,
    btnType: String

  }
}
</script>

<style scoped>

</style>
