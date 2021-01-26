package fr.keinz.surviePlugin.rank;

public enum RankList {
	ADMINISTRATEUR(0, 100, "§4[Admin] ", "", " : §f"),
	
	SUPERMODO(1, 90, "§9[SuperModo] ", "", " : §b"),
	
	MODERATEUR(2, 80, "§1[Modo] ", "", " : §3"),
	
	DEVELOPPEUR(3, 70, "§5[Developpeur] ", "", " : §f"),
	
	TESTEUR(4, 20, "§8[Testeur] ", "", " : §f"),
	
	HELPEUR(5, 20, "§2[Helpeur] ", "", " : §f"),
	
	BUILDEUR(5, 20, "§d[Builder] ", "", " : §f"),
	
	ASSISTANT(6, 10, "§e[Assistant] ", "", " : §f"),
	
	CUSTOM(7, 6, "§f[Custom] ", "", " : §f"),
	
	CHANCELIER(8, 5, "§b[Chancelier] ", "", " : §b"),
	
	ASSASSIN(9, 4, "§1[Assassin] ", "", " : §f"),
	
	GUERRIER(10, 3, "§c[Guerrier] ", "", " : §f"),
	
	SURVIVANT(11, 2, "§6[Survivant] ", "", " : §f"),
	
	NOVICE(12, 1, "§f[Novice] ", "", " : §7");
	
	private final int power, id;
	private final String prefix, suffix, chatSeparator;
	
	private RankList(int id, int power, String prefix, String suffix, String chatSeparator) {
		this.id = id;
		this.power = power;
		this.prefix = prefix;
		this.suffix = suffix;
		this.chatSeparator = chatSeparator;
	}
	
	public final int getId() {
		return id;
	}
	
	public final int getPower() {
		return power;
	}
	
	public final String getPrefix() {
		return prefix;
	}
	
	public final String getSuffix() {
		return suffix;
	}
	
	public final String getChatSeparator() {
		return chatSeparator;
	}
	
	public final String getName() {
		return this.toString();
	}
}
