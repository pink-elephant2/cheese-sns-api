package com.api.sns.cheese.consts;

/**
 * 共通定数クラス
 */
public class CommonConst {

	/**
	 * システムアカウント
	 */
	public static final class SystemAccount {
		/** 管理者アカウントID */
		public static final Integer ADMIN_ID = 1;
		/** ゲストアカウントID */
		public static final Integer GUEST_ID = 2;
		/** バッチアカウントID */
		public static final Integer BATCH_ID = 3;
	}

	/**
	 * 削除フラグ
	 */
	public static final class DeletedFlag {
		/** 未削除 */
		public static final String OFF = "0";
		/** 削除済み */
		public static final String ON = "1";
	}

}
