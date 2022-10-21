<template>
  <div class="my-button">
    <q-btn type="{{ btnType }}" color="black" >{{ btnLabel }}</q-btn>
  </div>
</template>

<script>
export default {
  name: "MyButton",
  props: {
    btnLabel: String,
    btnType: String
  }

}
</script>

<style scoped>

</style>
