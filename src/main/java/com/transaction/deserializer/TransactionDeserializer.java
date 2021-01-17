package com.transaction.deserializer;

import java.io.IOException;
import java.math.BigDecimal;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.transaction.entity.Transaction;

public class TransactionDeserializer extends JsonDeserializer<Transaction> {

	@Override
	public Transaction deserialize(JsonParser jsonParser, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		JsonNode transactionNode = jsonParser.getCodec().readTree(jsonParser);
		JsonNode thisAccountNode = transactionNode.get("this_account");
		JsonNode otherAccountNode = transactionNode.get("other_account");
		JsonNode holderNode = otherAccountNode.get("holder");
		JsonNode metadataNode = otherAccountNode.get("metadata");
		JsonNode detailsNode = transactionNode.get("details");
		JsonNode valueNode = detailsNode.get("value");
		
		Transaction transaction = new Transaction();
		
		transaction.setId(transactionNode.get("id").textValue());
		transaction.setAccountId(thisAccountNode.get("id").asText());
		transaction.setCounterPartyAccount(otherAccountNode.get("number").textValue());
		transaction.setCounterPartyName(holderNode.get("name").textValue());
		transaction.setCounterPartyLogoPath(metadataNode.get("image_URL").textValue());
		transaction.setInstructedAmount(new BigDecimal(valueNode.get("amount").asText("0")));
		transaction.setInstructedCurrency(valueNode.get("currency").textValue());
		transaction.setTransactionAmount(new BigDecimal(valueNode.get("amount").asText("0")));
		transaction.setTransactionCurrency(valueNode.get("currency").textValue());
		transaction.setTransactionType(detailsNode.get("type").textValue());
		transaction.setDescription(detailsNode.get("description").textValue());
		return transaction;
	}

}
