package com.usecase.reportgen.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "alphavantageentries") // Specify your MongoDB collection name
public class AlphaVantageEntry {

    @Id
    private String id; // MongoDB document ID
    
    private String accumulatedDepreciationAmortizationPPE;
    private String capitalLeaseObligations;
    private String cashAndCashEquivalentsAtCarryingValue;
    private String cashAndShortTermInvestments;
    private String commonStock;
    private String commonStockSharesOutstanding;
    private String currentAccountsPayable;
    private String currentDebt;
    private String currentLongTermDebt;
    private String currentNetReceivables;
    private String deferredRevenue;
    private String documentRef;
    private String fiscalDateEnding;
    private String goodwill;
    private String intangibleAssets;
    private String intangibleAssetsExcludingGoodwill;
    private String inventory;
    private String investments;
    private String longTermDebt;
    private String longTermDebtNoncurrent;
    private String longTermInvestments;
    private String otherCurrentAssets;
    private String otherCurrentLiabilities;
    private String otherNonCurrentAssets;
    private String otherNonCurrentLiabilities;
    private String propertyPlantEquipment;
    private String reportedCurrency;
    private String retainedEarnings;
    private String shortLongTermDebtTotal;
    private String shortTermDebt;
    private String shortTermInvestments;
    private String totalAssets;
    private String totalCurrentAssets;
    private String totalCurrentLiabilities;
    private String totalLiabilities;
    private String totalNonCurrentAssets;
    private String totalNonCurrentLiabilities;
    private String totalShareholderEquity;
    private String treasuryStock;

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccumulatedDepreciationAmortizationPPE() {
        return accumulatedDepreciationAmortizationPPE;
    }

    public void setAccumulatedDepreciationAmortizationPPE(String accumulatedDepreciationAmortizationPPE) {
        this.accumulatedDepreciationAmortizationPPE = accumulatedDepreciationAmortizationPPE;
    }

    public String getCapitalLeaseObligations() {
        return capitalLeaseObligations;
    }

    public void setCapitalLeaseObligations(String capitalLeaseObligations) {
        this.capitalLeaseObligations = capitalLeaseObligations;
    }

    public String getCashAndCashEquivalentsAtCarryingValue() {
        return cashAndCashEquivalentsAtCarryingValue;
    }

    public void setCashAndCashEquivalentsAtCarryingValue(String cashAndCashEquivalentsAtCarryingValue) {
        this.cashAndCashEquivalentsAtCarryingValue = cashAndCashEquivalentsAtCarryingValue;
    }

	public String getCashAndShortTermInvestments() {
		return cashAndShortTermInvestments;
	}

	public void setCashAndShortTermInvestments(String cashAndShortTermInvestments) {
		this.cashAndShortTermInvestments = cashAndShortTermInvestments;
	}

	public String getCommonStock() {
		return commonStock;
	}

	public void setCommonStock(String commonStock) {
		this.commonStock = commonStock;
	}

	public String getCommonStockSharesOutstanding() {
		return commonStockSharesOutstanding;
	}

	public void setCommonStockSharesOutstanding(String commonStockSharesOutstanding) {
		this.commonStockSharesOutstanding = commonStockSharesOutstanding;
	}

	public String getCurrentAccountsPayable() {
		return currentAccountsPayable;
	}

	public void setCurrentAccountsPayable(String currentAccountsPayable) {
		this.currentAccountsPayable = currentAccountsPayable;
	}

	public String getCurrentDebt() {
		return currentDebt;
	}

	public void setCurrentDebt(String currentDebt) {
		this.currentDebt = currentDebt;
	}

	public String getCurrentLongTermDebt() {
		return currentLongTermDebt;
	}

	public void setCurrentLongTermDebt(String currentLongTermDebt) {
		this.currentLongTermDebt = currentLongTermDebt;
	}

	public String getCurrentNetReceivables() {
		return currentNetReceivables;
	}

	public void setCurrentNetReceivables(String currentNetReceivables) {
		this.currentNetReceivables = currentNetReceivables;
	}

	public String getDeferredRevenue() {
		return deferredRevenue;
	}

	public void setDeferredRevenue(String deferredRevenue) {
		this.deferredRevenue = deferredRevenue;
	}

	public String getDocumentRef() {
		return documentRef;
	}

	public void setDocumentRef(String documentRef) {
		this.documentRef = documentRef;
	}

	public String getFiscalDateEnding() {
		return fiscalDateEnding;
	}

	public void setFiscalDateEnding(String fiscalDateEnding) {
		this.fiscalDateEnding = fiscalDateEnding;
	}

	public String getGoodwill() {
		return goodwill;
	}

	public void setGoodwill(String goodwill) {
		this.goodwill = goodwill;
	}

	public String getIntangibleAssets() {
		return intangibleAssets;
	}

	public void setIntangibleAssets(String intangibleAssets) {
		this.intangibleAssets = intangibleAssets;
	}

	public String getIntangibleAssetsExcludingGoodwill() {
		return intangibleAssetsExcludingGoodwill;
	}

	public void setIntangibleAssetsExcludingGoodwill(String intangibleAssetsExcludingGoodwill) {
		this.intangibleAssetsExcludingGoodwill = intangibleAssetsExcludingGoodwill;
	}

	public String getInventory() {
		return inventory;
	}

	public void setInventory(String inventory) {
		this.inventory = inventory;
	}

	public String getInvestments() {
		return investments;
	}

	public void setInvestments(String investments) {
		this.investments = investments;
	}

	public String getLongTermDebt() {
		return longTermDebt;
	}

	public void setLongTermDebt(String longTermDebt) {
		this.longTermDebt = longTermDebt;
	}

	public String getLongTermDebtNoncurrent() {
		return longTermDebtNoncurrent;
	}

	public void setLongTermDebtNoncurrent(String longTermDebtNoncurrent) {
		this.longTermDebtNoncurrent = longTermDebtNoncurrent;
	}

	public String getLongTermInvestments() {
		return longTermInvestments;
	}

	public void setLongTermInvestments(String longTermInvestments) {
		this.longTermInvestments = longTermInvestments;
	}

	public String getOtherCurrentAssets() {
		return otherCurrentAssets;
	}

	public void setOtherCurrentAssets(String otherCurrentAssets) {
		this.otherCurrentAssets = otherCurrentAssets;
	}

	public String getOtherCurrentLiabilities() {
		return otherCurrentLiabilities;
	}

	public void setOtherCurrentLiabilities(String otherCurrentLiabilities) {
		this.otherCurrentLiabilities = otherCurrentLiabilities;
	}

	public String getOtherNonCurrentAssets() {
		return otherNonCurrentAssets;
	}

	public void setOtherNonCurrentAssets(String otherNonCurrentAssets) {
		this.otherNonCurrentAssets = otherNonCurrentAssets;
	}

	public String getOtherNonCurrentLiabilities() {
		return otherNonCurrentLiabilities;
	}

	public void setOtherNonCurrentLiabilities(String otherNonCurrentLiabilities) {
		this.otherNonCurrentLiabilities = otherNonCurrentLiabilities;
	}

	public String getPropertyPlantEquipment() {
		return propertyPlantEquipment;
	}

	public void setPropertyPlantEquipment(String propertyPlantEquipment) {
		this.propertyPlantEquipment = propertyPlantEquipment;
	}

	public String getReportedCurrency() {
		return reportedCurrency;
	}

	public void setReportedCurrency(String reportedCurrency) {
		this.reportedCurrency = reportedCurrency;
	}

	public String getRetainedEarnings() {
		return retainedEarnings;
	}

	public void setRetainedEarnings(String retainedEarnings) {
		this.retainedEarnings = retainedEarnings;
	}

	public String getShortLongTermDebtTotal() {
		return shortLongTermDebtTotal;
	}

	public void setShortLongTermDebtTotal(String shortLongTermDebtTotal) {
		this.shortLongTermDebtTotal = shortLongTermDebtTotal;
	}

	public String getShortTermDebt() {
		return shortTermDebt;
	}

	public void setShortTermDebt(String shortTermDebt) {
		this.shortTermDebt = shortTermDebt;
	}

	public String getShortTermInvestments() {
		return shortTermInvestments;
	}

	public void setShortTermInvestments(String shortTermInvestments) {
		this.shortTermInvestments = shortTermInvestments;
	}

	public String getTotalAssets() {
		return totalAssets;
	}

	public void setTotalAssets(String totalAssets) {
		this.totalAssets = totalAssets;
	}

	public String getTotalCurrentAssets() {
		return totalCurrentAssets;
	}

	public void setTotalCurrentAssets(String totalCurrentAssets) {
		this.totalCurrentAssets = totalCurrentAssets;
	}

	public String getTotalCurrentLiabilities() {
		return totalCurrentLiabilities;
	}

	public void setTotalCurrentLiabilities(String totalCurrentLiabilities) {
		this.totalCurrentLiabilities = totalCurrentLiabilities;
	}

	public String getTotalLiabilities() {
		return totalLiabilities;
	}

	public void setTotalLiabilities(String totalLiabilities) {
		this.totalLiabilities = totalLiabilities;
	}

	public String getTotalNonCurrentAssets() {
		return totalNonCurrentAssets;
	}

	public void setTotalNonCurrentAssets(String totalNonCurrentAssets) {
		this.totalNonCurrentAssets = totalNonCurrentAssets;
	}

	public String getTotalNonCurrentLiabilities() {
		return totalNonCurrentLiabilities;
	}

	public void setTotalNonCurrentLiabilities(String totalNonCurrentLiabilities) {
		this.totalNonCurrentLiabilities = totalNonCurrentLiabilities;
	}

	public String getTotalShareholderEquity() {
		return totalShareholderEquity;
	}

	public void setTotalShareholderEquity(String totalShareholderEquity) {
		this.totalShareholderEquity = totalShareholderEquity;
	}

	public String getTreasuryStock() {
		return treasuryStock;
	}

	public void setTreasuryStock(String treasuryStock) {
		this.treasuryStock = treasuryStock;
	}

    // Add similar getters and setters for all other fields...
}

