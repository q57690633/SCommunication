package com.huxin.communication.listener;

import com.huxin.communication.entity.GetMessageEntity;

import java.util.List;

public interface GetMessageListener {

    void getMessage(List<GetMessageEntity> list);
}
