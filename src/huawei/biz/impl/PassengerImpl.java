package huawei.biz.impl;

import huawei.biz.CardManager;
import huawei.biz.Conductor;
import huawei.biz.Passenger;
import huawei.biz.SubwayManager;
import huawei.exam.CardEnum;
import huawei.exam.ReturnCodeEnum;
import huawei.exam.SubwayException;
import huawei.model.Card;
import huawei.model.ConsumeRecord;
import huawei.model.Subways;
import huawei.model.Subways.DistanceInfo;
import javafx.util.Pair;
import sun.java2d.cmm.kcms.KcmsServiceProvider;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import javax.sql.RowSet;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

/**
 * <p>
 * Title: 待考生实现类
 * </p>
 *
 * <p>
 * Description: 乘客
 * </p>
 *
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 *
 * <p>
 * Company:
 * </p>
 *
 * @author
 * @version 1.0 OperationCenter V100R002C20, 2015/9/7]
 */
public class PassengerImpl implements Passenger {
	private Conductor conductor;
	private CardManager cardManager;
	private SubwayManager subwayManager;
	
	public PassengerImpl(Conductor conductor, CardManager cardManager, SubwayManager subwayManager) {
		this.conductor = conductor;
		this.cardManager = cardManager;
		this.subwayManager = subwayManager;
	}
	
	@Override
	public Card buyCard(String enterStation, String exitStation) throws SubwayException {
		// TODO 待考生实现
		
		Integer price = subwayManager.calculatePriceForStations(enterStation, exitStation);
		
		Card card = conductor.buyCard(enterStation, exitStation);
		card.setMoney(price);

		return card;
	}

	@Override
	public Card buyCard(CardEnum cardEnum, int money) throws SubwayException {
		// TODO 待考生实现
		
		Card card = conductor.buyCard(cardEnum, money);
		
		return card;
	}

	@Override
	public Card recharge(String cardId, int money) throws SubwayException {
		// TODO 待考生实现
		
		Card card = conductor.recharge(cardId, money);
		
		return card;
	}

	@Override
	public Card queryCard(String cardId) throws SubwayException {
		// TODO 待考生实现
		
		Card card = cardManager.queryCard(cardId);
		
		return card;
	}

	@Override
	public Card deleteCard(String cardId) throws SubwayException {
		// TODO 待考生实现
		
		Card card = conductor.deleteCard(cardId);
		
		return card;
	}

	@Override
	public Card takeSubway(String cardId, String enterStation, String enterTime, String exitStation, String exitTime)
			throws SubwayException {
		// TODO 待考生实现
		
		Card card = subwayManager.takeSubway(cardId, enterStation, enterTime, exitStation, exitTime);
		
		return card;
	}

	@Override
	public List<ConsumeRecord> queryConsumeRecord(String cardId) throws SubwayException {
		// TODO 待考生实现
		
		List<ConsumeRecord> list = cardManager.queryConsumeRecord(cardId);
		
		return list;
	}
}