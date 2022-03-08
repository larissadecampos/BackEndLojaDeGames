package com.generation.games.lojaGames.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.generation.games.lojaGames.model.Produto;

	@Repository
	public interface ProdutoRepository extends JpaRepository<Produto, Long> {

		public List<Produto> findAllByNomeContainingIgnoreCase(String nome);
		public List<Produto> findAllByConsoleContainingIgnoreCase(String nome);
		
	}