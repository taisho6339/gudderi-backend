package com.gudderi.api.component.context;

/**
 * 実行コンテキストを維持するための戦略インタフェース。
 */
public interface HolderStrategy {

    /**
     * コンテキストを解放します。
     */
    void clear();

    /**
     * コンテキストを取得します。
     *
     * @return 実行コンテキスト
     */
    ExecContext get();

    /**
     * コンテキストを設定します。
     *
     * @param ctx 実行コンテキスト
     */
    void set(ExecContext ctx);

    /**
     * 新規コンテキストの作成します。
     *
     * @return 実行コンテキスト
     */
    ExecContext createEmptyContext();

}