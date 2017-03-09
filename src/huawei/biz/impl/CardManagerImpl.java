package huawei.biz.impl;

import huawei.biz.CardManager;
import huawei.exam.CardEnum;
import huawei.exam.ReturnCodeEnum;
import huawei.exam.SubwayException;
import huawei.model.Card;
import huawei.model.ConsumeRecord;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

/**
 * <p>Title: 待考生实现类</p>
 *
 * <p>Description: 卡票中心</p>
 *
 * <p>Copyright: Copyright (c) 2013</p>
 *
 * <p>Company: </p>
 *
 * @author
 * @version 1.0 OperationCenter V100R002C20, 2015/9/7]
 */
public class CardManagerImpl implements CardManager
{
	private Map<String, Card> cards = new HashMap<String, Card>();
	private Map<String, List<ConsumeRecord>> records = new HashMap<String, List<ConsumeRecord>>();
	
	private int currentId = 0;
	
	private int cardCount = 0;
	
	private Card getNewCard() throws SubwayException {
		Card card = new Card();
    	
    	if (cardCount < 0 || cardCount >= Integer.MAX_VALUE)
    		throw new SubwayException(ReturnCodeEnum.E08, card);
    	
    	String cardId = Integer.toString(currentId);
    	card.setCardId(cardId);
    	cards.put(cardId, card);
    	
    	cardCount++;
    	currentId++;
    	
       	while (cards.containsKey(currentId)) {
       		if (currentId < Integer.MAX_VALUE) {
       			currentId++;
       		} else {
       			currentId = 0;
       		}
       	}
    	
    	return card;
	}
	
    @Override
    public Card buyCard(String enterStation, String exitStation)
        throws SubwayException
    {
        //TODO 待考生实现
    	
    	Card card = getNewCard();
    	card.setCardType(CardEnum.A);
    	
        return card;
    }

    public boolean hasCardEnum(CardEnum cardEnum) {
		for (CardEnum value : CardEnum.values()) {
			if (value.equals(cardEnum)) return true;
		}
		return false;
	}
    
    @Override
    public Card buyCard(CardEnum cardEnum, int money)
        throws SubwayException
    {
        //TODO 待考生实现
    	
    	if (!hasCardEnum(cardEnum)) throw new SubwayException(ReturnCodeEnum.E04, null);
    	
    	Card card = getNewCard();
    	card.setCardType(cardEnum);
    	card.setMoney(money);
    	
        return card;
    }

    @Override
    public Card recharge(String cardId, int money)
        throws SubwayException
    {
        //TODO 待考生实现
    	
    	Card card = queryCard(cardId);
    	
    	card.setMoney(card.getMoney() + money);
    	
        return card;
    }

    @Override
    public Card queryCard(String cardId) throws SubwayException
    {
        //TODO 待考生实现
    	
    	if (!cards.containsKey(cardId)) {
    		Card card = new Card();
    		card.setCardId(cardId);
        	card.setCardType(CardEnum.E);
    		
    		throw new SubwayException(ReturnCodeEnum.E06, card);
    	}
    	
        return cards.get(cardId);
    }

    @Override
    public Card deleteCard(String cardId)
        throws SubwayException
    {
        //TODO 待考生实现
        
    	Card card = queryCard(cardId);
    	
        cards.remove(cardId);
        records.remove(cardId);
        
        return card;
    }

    @Override
    public Card consume(String cardId, int billing)
        throws SubwayException
    {
        //TODO 待考生实现
    	Card card = queryCard(cardId);
    	
    	if (card.getCardType().equals(CardEnum.A)) {
    		cards.remove(cardId);
    	}
    	
    	if (card.getMoney() < billing) throw new SubwayException(ReturnCodeEnum.E02, card);
    	
    	card.setMoney(card.getMoney() - billing);
    	
    	if (card.getMoney() < 20) throw new SubwayException(ReturnCodeEnum.E03, card);
    	
        return card;
    }

    @Override
    public List<ConsumeRecord> queryConsumeRecord(String cardId)
        throws SubwayException
    {
        //TODO 待考生实现
    	
    	queryCard(cardId);
    	
    	if (records.containsKey(cardId)) {
    		return records.get(cardId);
    	} else {
    		List<ConsumeRecord> list = new LinkedList<ConsumeRecord>();
    		records.put(cardId, list);
    		return list;
    	}
    }
}