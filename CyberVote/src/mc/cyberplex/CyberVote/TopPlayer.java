package mc.cyberplex.CyberVote;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

import mc.cyberplex.CyberVote.Tree.BinarySearchTree;
import mc.cyberplex.CyberVote.Tree.TreeIterator;

public class TopPlayer {

	Main main = Main.getMain();

	public String getTopDaily(int index) {

		if(main.getConfig().getConfigurationSection("Daily.players") != null) {
			BinarySearchTree tree =  new BinarySearchTree();
			Set<String> tempPlayers = main.getConfig().getConfigurationSection("Daily.players").getKeys(false);
			String [] names = new String[tempPlayers.size()];
			ArrayList<String> playerName = new ArrayList<String>();
			ArrayList<Integer> voteCount = new ArrayList<Integer>();

			tempPlayers.toArray(names);

			if(index <= tempPlayers.size()) {
				for(int i = 0; i < tempPlayers.size(); i++) {
					playerName.add(names[i]);
					voteCount.add(main.getConfig().getInt("Daily.players." + names[i]));
				}

				for(int i = 0; i < tempPlayers.size(); i++) {			
					tree.insert(voteCount.get(i), playerName.get(i));			
				}

				TreeIterator iter = new TreeIterator(tree);
				iter.setInorder();

				ArrayList<String> list = new ArrayList<String>();

				while(iter.hasNext()) {
					list.add(iter.next());
				}

				Collections.reverse(list);

				return list.get(--index);
			} else {
				return "";
			}

		} else {
			return "";
		}
	}

	public String getTopWeekly(int index) {

		if(main.getConfig().getConfigurationSection("Weekly.players") != null) {
			BinarySearchTree tree =  new BinarySearchTree();
			Set<String> tempPlayers = main.getConfig().getConfigurationSection("Weekly.players").getKeys(false);
			String [] names = new String[tempPlayers.size()];
			ArrayList<String> playerName = new ArrayList<String>();
			ArrayList<Integer> voteCount = new ArrayList<Integer>();

			tempPlayers.toArray(names);

			if(index <= tempPlayers.size()) {
				for(int i = 0; i < tempPlayers.size(); i++) {
					playerName.add(names[i]);
					voteCount.add(main.getConfig().getInt("Weekly.players." + names[i]));
				}

				for(int i = 0; i < tempPlayers.size(); i++) {
					tree.insert(voteCount.get(i), playerName.get(i));			
				}

				TreeIterator iter = new TreeIterator(tree);
				iter.setInorder();

				ArrayList<String> list = new ArrayList<String>();

				while(iter.hasNext()) {
					list.add(iter.next());
				}

				Collections.reverse(list);

				return list.get(--index);
			} else {
				return "";
			}
		} else {
			return "";
		}
	}

	public String getTopMonthly(int index) {

		if(main.getConfig().getConfigurationSection("Monthly.players") != null) {
			BinarySearchTree tree =  new BinarySearchTree();
			Set<String> tempPlayers = main.getConfig().getConfigurationSection("Monthly.players").getKeys(false);
			String [] names = new String[tempPlayers.size()];
			ArrayList<String> playerName = new ArrayList<String>();
			ArrayList<Integer> voteCount = new ArrayList<Integer>();

			tempPlayers.toArray(names);

			if(index <= tempPlayers.size()) {
				for(int i = 0; i < tempPlayers.size(); i++) {

					playerName.add(names[i]);
					voteCount.add(main.getConfig().getInt("Monthly.players." + names[i]));

				}

				for(int i = 0; i < tempPlayers.size(); i++) {			
					tree.insert(voteCount.get(i), playerName.get(i));			
				}

				TreeIterator iter = new TreeIterator(tree);
				iter.setInorder();

				ArrayList<String> list = new ArrayList<String>();

				while(iter.hasNext()) {
					list.add(iter.next());
				}

				Collections.reverse(list);

				return list.get(--index);
			} else {
				return "";
			}
		} else {
			return "";
		}
	}

}
