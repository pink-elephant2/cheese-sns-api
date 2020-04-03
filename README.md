# cheese-sns-api

## API一覧

| API名 | メソッド | URI | 認証必須 |
----|----|----|----
| ヘルスチェックAPI							| GET		| /api/v1/status												| - |
| アカウント登録API							| POST		| /api/v1/account												| - |
| アカウント取得API							| GET		| /api/v1/account/{loginId}										| - |
| アカウント更新API							| POST		| /api/v1/user/{loginId}/account/profile						| ○ |
| アカウント画像更新API						| POST		| /api/v1/user/{loginId}/account/image							| ○ |
| アカウントブロックAPI						| POST		| /api/v1/account/{loginId}/block								| - |
| アカウント通報API							| POST		| /api/v1/account/{loginId}/report								| - |
| アクティビティ取得API						| GET		| /api/v1/user/{loginId}/me										| ○ |
| フォローワーアクティビティ取得API			| GET		| /api/v1/user/{loginId}/following								| ○ |
| ログインAPI								| POST		| /api/v1/login													| - |
| ログインチェックAPI						| GET		| /api/v1/check/login											| - |
| ログアウトAPI								| POST		| /api/v1/logout												| ○ |
| FacebookログインAPI						| GET		| /api/v1/login/facebook										| - |
| パスワードリマインダーメール送信API		| POST		| /api/v1/password/reminder										| - |
| パスワードリマインダートークンチェックAPI	| GET		| /api/v1/password/reminder/token/{token}						| - |
| パスワード再設定API						| POST		| /api/v1/password/reset										| - |
| お問合せ登録API							| POST		| /api/v1/contact												| - |
| フォロー取得API							| GET		| /api/v1/account/{loginId}/follow								| - |
| フォローワー取得API						| GET		| /api/v1/account/{loginId}/follower							| - |
| フォロー登録API							| POST		| /api/v1/user/{loginId}/follow									| ○ |
| フォロー解除API							| POST		| /api/v1/user/{loginId}/unfollow								| ○ |
| 写真取得API								| GET		| /api/v1/photo/{cd}											| - |
| 写真一覧取得API							| GET		| /api/v1/photo													| - |
| 写真登録API								| POST		| /api/v1/user/{loginId}/photo									| ○ |
| 写真更新API								| PUT		| /api/v1/user/{loginId}/photo									| ○ |
| 写真削除API								| DELETE	| /api/v1/user/{loginId}/photo									| ○ |
| 写真通報API								| POST		| /api/v1/photo/{cd}/report										| - |
| 動画登録API								| POST		| /api/v1/user/{loginId}/video									| ○ |
| 検索サジェストAPI							| GET		| /api/v1/photo/suggest/{keyword}								| - |
| いいねAPI									| POST		| /api/v1/user/{loginId}/photo/{cd}/like						| ○ |
| いいね解除API								| POST		| /api/v1/user/{loginId}/photo/{cd}/dislike						| ○ |
| コメントいいねAPI							| POST		| /api/v1/user/{loginId}/photo/{cd}/comment/{commentCd}/like	| ○ |
| コメントいいね解除API						| POST		| /api/v1/user/{loginId}/photo/{cd}/comment/{commentCd}/dislike	| ○ |
| ブックマーク取得API						| GET		| /api/v1/user/{loginId}/bookmark/{cd}							| ○ |
| ブックマーク一覧取得API					| GET		| /api/v1/user/{loginId}/bookmark								| ○ |
| ブックマーク登録API						| POST		| /api/v1/user/{loginId}/bookmark/{cd}							| ○ |
| ブックマーク削除API						| DELETE	| /api/v1/user/{loginId}/bookmark/{cd}							| ○ |
