package net.anotheria.moskito.webui.shared.api.filter;

/**
 * TODO comment this class
 *
 * @author lrosenberg
 * @since 20.04.15 11:20
 */
public class StartsWithMatcher extends BaseMatcher implements Matcher {

	public StartsWithMatcher(String aCriteria){
		super(aCriteria);
	}

	@Override
	public boolean doesMatch(String target) {
		return target.startsWith(getCriteria());
	}

}
