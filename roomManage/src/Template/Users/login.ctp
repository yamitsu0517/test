<h1 class="page-header">ログイン</h1>
<?php

echo $this->Form->create($user);
echo $this->Form->input('email', array(
  'label' => 'メールアドレス:'
));
echo $this->Form->input('password', array(
  'label' => 'パスワード:'
));
echo $this->Form->button('ログイン');
echo $this->Form->end();
