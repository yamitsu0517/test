package com.example.shosekikun.common

class Const {
    companion object {
        const val COMMON_ERROR_MESSAGE = "処理中にエラーが発生しました。"
        const val COMMON_DELETE_NOT_FOUND = "該当データがありません。"
        const val CATEGORY_FORM_ERROR_MESSAGE1 = "カテゴリ名を設定してください。"
        const val CATEGORY_REGIST_SUCCESS = "カテゴリの登録が完了しました。"
        const val CATEGORY_UPDATE_SUCCESS = "カテゴリの更新が完了しました。"
        const val CATEGORY_DELETE_SUCCESS = "カテゴリの削除が完了しました。"
        const val CATEGORY_DELETE_BOOK_EXISTS = "書籍に登録されているカテゴリは削除できません。"

        const val AUTHOR_FORM_ERROR_MESSAGE1 = "著者名を設定してください。"
        const val AUTHOR_REGIST_SUCCESS = "著者の登録が成功しました。"
        const val AUTHOR_EDIT_SUCCESS = "著者の変更が完了しました。"
        const val AUTHOR_DELETE_SUCCESS = "著者の削除が完了しました。"
        const val AUTHOR_DELETE_EXISTS_BOOK = "書籍に登録されている著者は削除できません。"

        const val BOOK_REGIST_SUCCESS = "書籍の追加が完了しました。"
        const val BOOK_EDIT_SUCCESS = "書籍の更新が完了しました。"
        const val BOOK_DELETE_SUCCESS = "書籍の削除が完了しました。"

        const val VALID_MESSAGE_TITLE = "タイトルを入力してください。"
        const val VALID_MESSAGE_PRICE = "価格を入力してください。"
        const val VALID_MESSAGE_CATEGORY = "カテゴリを指定してください。"
        const val VALID_MESSAGE_AUTHOR = "著者を指定してください。"

    }
}
