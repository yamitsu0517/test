<div class="body">

    <div class="left" id="left">
        左
    </div>
    
    <div class="main">
        メイン
    </div>
    
    <div class="right">
        右
    </div>

</div>
<script>
const leftId = document.getElementById("left");

leftId.addEventListener("click", (e) => {
    console.log(e.target);
});
</script>
<style type="text/css">
.left {
    width: 20px;
    height: 20px;
    color: red;
    background-color: red;
}
</style>
