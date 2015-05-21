/*
 * Copyright 2015 qiwenge
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.qiwenge.android.mvp.model;

import android.content.Context;
import android.text.TextUtils;

import com.liuguangqiang.android.mvp.BaseRequestUi;
import com.loopj.android.http.RequestParams;
import com.qiwenge.android.async.AsyncUtils;
import com.qiwenge.android.entity.BookList;
import com.qiwenge.android.utils.ApiUtils;
import com.qiwenge.android.utils.http.JsonResponseHandler;

import javax.inject.Inject;

/**
 * Created by Eric on 15/5/21.
 */
public class BookModel {

    @Inject
    public BookModel() {
    }

    public void getBooks(Context context, int pageindex, BaseRequestUi ui) {
        getBooks(context, pageindex, null, ui);
    }

    public void getBooks(Context context, int pageindex, String keyword, final BaseRequestUi ui) {
        String url = ApiUtils.getBooks();
        RequestParams params = new RequestParams();
        params.put("page", pageindex);
        if (!TextUtils.isEmpty(keyword))
            params.put("title", keyword);

        AsyncUtils.getBooks(context, url, params, new JsonResponseHandler<BookList>(BookList.class) {

            @Override
            public void onSuccess(BookList result) {
                if (result != null && result.result != null) {
                    ui.requestSuccess(result.result);
                }
            }

            @Override
            public void onFinish() {
                ui.requestFinished();
            }
        });
    }

}
