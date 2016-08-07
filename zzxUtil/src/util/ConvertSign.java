package com.fid.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class ConvertSign {
	public final static Map<String, String> HTML_CHAR = new HashMap<String, String>();  
	static {  
		HTML_CHAR.put("&Alpha;", "Α");
		HTML_CHAR.put("&Beta;", "Β");
		HTML_CHAR.put("&Gamma;", "Γ");
		HTML_CHAR.put("&Delta;", "Δ");
		HTML_CHAR.put("&Epsilon;", "Ε");
		HTML_CHAR.put("&Zeta;", "Ζ");
		HTML_CHAR.put("&Eta;", "Η");
		HTML_CHAR.put("&Theta;", "Θ");
		HTML_CHAR.put("&Iota;", "Ι");
		HTML_CHAR.put("&Kappa;", "Κ");
		HTML_CHAR.put("&Lambda;", "Λ");
		HTML_CHAR.put("&Mu;", "Μ");
		HTML_CHAR.put("&Nu;", "Ν");
		HTML_CHAR.put("&Xi;", "Ξ");
		HTML_CHAR.put("&Omicron;", "Ο");
		HTML_CHAR.put("&Pi;", "Π");
		HTML_CHAR.put("&Rho;", "Ρ");
		HTML_CHAR.put("&Sigma;", "Σ");
		HTML_CHAR.put("&Tau;", "Τ");
		HTML_CHAR.put("&Upsilon;", "Υ");
		HTML_CHAR.put("&Phi;", "Φ");
		HTML_CHAR.put("&Chi;", "Χ");
		HTML_CHAR.put("&Psi;", "Ψ");
		HTML_CHAR.put("&Omega;", "Ω");
		HTML_CHAR.put("&alpha;", "α");
		HTML_CHAR.put("&beta;", "β");
		HTML_CHAR.put("&gamma;", "γ");
		HTML_CHAR.put("&delta;", "δ");
		HTML_CHAR.put("&epsilon;", "ε");
		HTML_CHAR.put("&zeta;", "ζ");
		HTML_CHAR.put("&eta;", "η");
		HTML_CHAR.put("&theta;", "θ");
		HTML_CHAR.put("&iota;", "ι");
		HTML_CHAR.put("&kappa;", "κ");
		HTML_CHAR.put("&lambda;", "λ");
		HTML_CHAR.put("&mu;", "μ");
		HTML_CHAR.put("&nu;", "ν");
		HTML_CHAR.put("&xi;", "ξ");
		HTML_CHAR.put("&omicron;", "ο");
		HTML_CHAR.put("&pi;", "π");
		HTML_CHAR.put("&rho;", "ρ");
		HTML_CHAR.put("&sigmaf;", "ς");
		HTML_CHAR.put("&sigma;", "σ");
		HTML_CHAR.put("&tau;", "τ");
		HTML_CHAR.put("&upsilon;", "υ");
		HTML_CHAR.put("&phi;", "φ");
		HTML_CHAR.put("&chi;", "χ");
		HTML_CHAR.put("&psi;", "ψ");
		HTML_CHAR.put("&omega;", "ω");
		HTML_CHAR.put("&thetasym;", "ϑ");
		HTML_CHAR.put("&upsih;", "ϒ");
		HTML_CHAR.put("&piv;", "ϖ");
		HTML_CHAR.put("&bull;", "•");
		HTML_CHAR.put("&hellip;", "…");
		HTML_CHAR.put("&prime;", "′");
		HTML_CHAR.put("&Prime;", "″");
		HTML_CHAR.put("&oline;", "‾");
		HTML_CHAR.put("&frasl;", "⁄");
		HTML_CHAR.put("&weierp;", "℘");
		HTML_CHAR.put("&image;", "ℑ");
		HTML_CHAR.put("&real;", "ℜ");
		HTML_CHAR.put("&trade;", "™");
		HTML_CHAR.put("&alefsym;", "ℵ");
		HTML_CHAR.put("&larr;", "←");
		HTML_CHAR.put("&uarr;", "↑");
		HTML_CHAR.put("&rarr;", "→");
		HTML_CHAR.put("&darr;", "↓");
		HTML_CHAR.put("&harr;", "↔");
		HTML_CHAR.put("&crarr;", "↵");
		HTML_CHAR.put("&lArr;", "⇐");
		HTML_CHAR.put("&uArr;", "⇑");
		HTML_CHAR.put("&rArr;", "⇒");
		HTML_CHAR.put("&dArr;", "⇓");
		HTML_CHAR.put("&hArr;", "⇔");
		HTML_CHAR.put("&forall;", "∀");
		HTML_CHAR.put("&part;", "∂");
		HTML_CHAR.put("&exist;", "∃");
		HTML_CHAR.put("&empty;", "∅");
		HTML_CHAR.put("&nabla;", "∇");
		HTML_CHAR.put("&isin;", "∈");
		HTML_CHAR.put("&notin;", "∉");
		HTML_CHAR.put("&ni;", "∋");
		HTML_CHAR.put("&prod;", "∏");
		HTML_CHAR.put("&sum;", "∑");
		HTML_CHAR.put("&minus;", "−");
		HTML_CHAR.put("&lowast;", "∗");
		HTML_CHAR.put("&radic;", "√");
		HTML_CHAR.put("&prop;", "∝");
		HTML_CHAR.put("&infin;", "∞");
		HTML_CHAR.put("&ang;", "∠");
		HTML_CHAR.put("&and;", "∧");
		HTML_CHAR.put("&or;", "∨");
		HTML_CHAR.put("&cap;", "∩");
		HTML_CHAR.put("&cup;", "∪");
		HTML_CHAR.put("&int;", "∫");
		HTML_CHAR.put("&there4;", "∴");
		HTML_CHAR.put("&sim;", "∼");
		HTML_CHAR.put("&cong;", "≅");
		HTML_CHAR.put("&asymp;", "≈");
		HTML_CHAR.put("&ne;", "≠");
		HTML_CHAR.put("&equiv;", "≡");
		HTML_CHAR.put("&le;", "≤");
		HTML_CHAR.put("&ge;", "≥");
		HTML_CHAR.put("&sub;", "⊂");
		HTML_CHAR.put("&sup;", "⊃");
		HTML_CHAR.put("&nsub;", "⊄");
		HTML_CHAR.put("&sube;", "⊆");
		HTML_CHAR.put("&supe;", "⊇");
		HTML_CHAR.put("&oplus;", "⊕");
		HTML_CHAR.put("&otimes;", "⊗");
		HTML_CHAR.put("&perp;", "⊥");
		HTML_CHAR.put("&sdot;", "⋅");
		HTML_CHAR.put("&lceil;", "⌈");
		HTML_CHAR.put("&rceil;", "⌉");
		HTML_CHAR.put("&lfloor;", "⌊");
		HTML_CHAR.put("&rfloor;", "⌋");
		HTML_CHAR.put("&loz;", "◊");
		HTML_CHAR.put("&spades;", "♠");
		HTML_CHAR.put("&clubs;", "♣");
		HTML_CHAR.put("&hearts;", "♥");
		HTML_CHAR.put("&diams;", "♦");
		HTML_CHAR.put("&nbsp;", " ");
		HTML_CHAR.put("&iexcl;", "¡");
		HTML_CHAR.put("&cent;", "¢");
		HTML_CHAR.put("&pound;", "£");
		HTML_CHAR.put("&curren;", "¤");
		HTML_CHAR.put("&yen;", "¥");
		HTML_CHAR.put("&brvbar;", "¦");
		HTML_CHAR.put("&sect;", "§");
		HTML_CHAR.put("&uml;", "¨");
		HTML_CHAR.put("&copy;", "©");
		HTML_CHAR.put("&ordf;", "ª");
		HTML_CHAR.put("&laquo;", "«");
		HTML_CHAR.put("&not;", "¬");
		HTML_CHAR.put("&shy;", "­");
		HTML_CHAR.put("&reg;", "®");
		HTML_CHAR.put("&macr;", "¯");
		HTML_CHAR.put("&deg;", "°");
		HTML_CHAR.put("&plusmn;", "±");
		HTML_CHAR.put("&sup2;", "²");
		HTML_CHAR.put("&sup3;", "³");
		HTML_CHAR.put("&acute;", "´");
		HTML_CHAR.put("&micro;", "µ");
		HTML_CHAR.put("&quot;", "\"");
		HTML_CHAR.put("&ldquo;", "“");
		HTML_CHAR.put("&rdquo;", "”");
		HTML_CHAR.put("&lt;", "<");
		HTML_CHAR.put("&gt;", ">");
		HTML_CHAR.put(" ", "'");
	}  
	  
	public static final String toHTMLChar(String str) {  
	    if (str == null) {  
	        return null;  
	    }         
	   /* StringBuilder sb = new StringBuilder(str);  
	  
	    char tempChar;  
	    String tempStr;  
	    for (int i = 0; i < sb.length(); i++) {  
	        tempChar = sb.charAt(i);  
	        if (HTML_CHAR.containsKey(Character.toString(tempChar))) {  
	            tempStr = (String) HTML_CHAR.get(Character  
	                    .toString(tempChar));  
	            sb.replace(i, i + 1, tempStr);  
	            i += tempStr.length() - 1;  
	        }  
	    }  
	    return sb.toString(); */
	    for (Entry<String, String> entry : HTML_CHAR.entrySet()) {
			str = str.replace(entry.getKey(), entry.getValue());
		}
	    return str;
	}  
	
	
	public static final String toDataChar(String str) {  
	    if (str == null) {  
	        return null;  
	    }         
	   /* StringBuilder sb = new StringBuilder(str);  
	  
	    char tempChar;  
	    String tempStr;  
	    for (int i = 0; i < sb.length(); i++) {  
	        tempChar = sb.charAt(i);  
	        if (HTML_CHAR.containsKey(Character.toString(tempChar))) {  
	            tempStr = (String) HTML_CHAR.get(Character  
	                    .toString(tempChar));  
	            sb.replace(i, i + 1, tempStr);  
	            i += tempStr.length() - 1;  
	        }  
	    }  
	    return sb.toString(); */
	    for (Entry<String, String> entry : HTML_CHAR.entrySet()) {
			str = str.replace(entry.getValue(), entry.getKey());
		}
	    return str;
	}
}

